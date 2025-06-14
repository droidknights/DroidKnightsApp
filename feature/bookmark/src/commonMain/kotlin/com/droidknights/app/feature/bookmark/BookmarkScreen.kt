package com.droidknights.app.feature.bookmark

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.components.CircularProgressIndicator
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.model.session.Speaker
import com.droidknights.app.core.model.session.Tag
import com.droidknights.app.feature.bookmark.component.BackHandler
import com.droidknights.app.feature.bookmark.component.BookmarkCard
import com.droidknights.app.feature.bookmark.component.BookmarkItem
import com.droidknights.app.feature.bookmark.component.BookmarkTimelineItem
import com.droidknights.app.feature.bookmark.component.BookmarkTopAppBar
import com.droidknights.app.feature.bookmark.component.EditModeLeadingItem
import com.droidknights.app.feature.bookmark.component.RemoveBookmarkSnackBar
import com.droidknights.app.feature.bookmark.component.bookmarkSnackBarHeight
import com.droidknights.app.feature.bookmark.model.BookmarkItemUiState
import com.droidknights.app.feature.bookmark.model.BookmarkUiState
import droidknights.feature.bookmark.generated.resources.Res
import droidknights.feature.bookmark.generated.resources.drag_and_drop
import droidknights.feature.bookmark.generated.resources.empty_bookmark_item_description
import droidknights.feature.bookmark.generated.resources.ic_menu
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

private val contentPaddingBottom = 72.dp
internal val defaultPadding = 8.dp

@Composable
fun BookmarkRoute(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = koinViewModel(),
) {
    val bookmarkUiState by viewModel.bookmarkUiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { onShowErrorSnackBar(it) }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(KnightsTheme.colorScheme.surface)
            .systemBarsPadding(),
    ) {
        BookmarkContent(
            uiState = bookmarkUiState,
            toggleEditMode = viewModel::toggleEditMode,
            onSelectedItem = viewModel::selectSession,
            onDeletedSessions = viewModel::deleteSessions,
        )
    }
}

@Composable
private fun BookmarkContent(
    uiState: BookmarkUiState,
    toggleEditMode: () -> Unit,
    onSelectedItem: (Session) -> Unit,
    onDeletedSessions: () -> Unit,
) {
    when (uiState) {
        BookmarkUiState.Loading -> BookmarkLoading()
        is BookmarkUiState.Success -> BookmarkScreen(
            isEditMode = uiState.isEditMode,
            bookmarkItems = uiState.bookmarks.toImmutableList(),
            toggleEditMode = toggleEditMode,
            selectedSessionIds = uiState.selectedSessionIds,
            onSelectedItem = onSelectedItem,
            onDeletedSessions = onDeletedSessions,
            listContentBottomPadding = if (uiState.selectedSessionIds.isNotEmpty()) {
                contentPaddingBottom + bookmarkSnackBarHeight + defaultPadding
            } else {
                contentPaddingBottom
            },
        )
    }
}

@Composable
private fun BookmarkLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(40.dp))
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun BookmarkScreen(
    isEditMode: Boolean,
    bookmarkItems: ImmutableList<BookmarkItemUiState>,
    toggleEditMode: () -> Unit,
    selectedSessionIds: ImmutableSet<String>,
    onSelectedItem: (Session) -> Unit,
    onDeletedSessions: () -> Unit,
    listContentBottomPadding: Dp = contentPaddingBottom,
) {
    BackHandler(isEditMode) {
        toggleEditMode()
    }
    Box(
        contentAlignment = Alignment.BottomCenter,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = KnightsTheme.colorScheme.background),
        ) {
            BookmarkTopAppBar(
                isEditMode = isEditMode,
                onClickEditButton = toggleEditMode,
            )

            if (bookmarkItems.isEmpty()) {
                BookmarkEmptyScreen()
            }

            BookmarkList(
                listContentBottomPadding = listContentBottomPadding,
                bookmarkItems = bookmarkItems,
                selectedSessionIds = selectedSessionIds,
                isEditMode = isEditMode,
                onSelectedItem = onSelectedItem,
            )
        }

        AnimatedVisibility(
            visible = selectedSessionIds.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            RemoveBookmarkSnackBar(
                modifier = Modifier
                    .padding(bottom = contentPaddingBottom)
                    .padding(horizontal = defaultPadding),
                onClick = onDeletedSessions,
            )
        }
    }
}

@Composable
private fun BookmarkList(
    listContentBottomPadding: Dp,
    bookmarkItems: ImmutableList<BookmarkItemUiState>,
    selectedSessionIds: ImmutableSet<String>,
    isEditMode: Boolean,
    onSelectedItem: (Session) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = listContentBottomPadding),
    ) {
        items(
            items = bookmarkItems,
            key = { item -> item.session.id },
        ) { itemState ->
            val isSelected = selectedSessionIds.contains(itemState.session.id)
            BookmarkItem(
                modifier = Modifier
                    .background(
                        color = if (isSelected) {
                            KnightsTheme.colorScheme.borderColor
                        } else {
                            KnightsTheme.colorScheme.background
                        },
                    )
                    .padding(
                        end = if (isEditMode) 0.dp else 16.dp,
                    ),
                leadingContent = @Composable {
                    if (isEditMode) {
                        EditModeLeadingItem(
                            itemState = itemState,
                            selectedSessionIds = selectedSessionIds,
                            onSelectedItem = onSelectedItem,
                        )
                    } else {
                        BookmarkTimelineItem(
                            modifier = Modifier.padding(horizontal = defaultPadding),
                            sequence = itemState.sequence,
                            time = itemState.time,
                        )
                    }
                },
                midContent = @Composable {
                    BookmarkCard(
                        tagLabel = itemState.tagLabel,
                        room = itemState.session.room,
                        title = itemState.session.title,
                        speaker = itemState.speakerLabel,
                    )
                },
                isEditMode = isEditMode,
                trailingContent = @Composable {
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 18.dp)
                            .size(24.dp),
                        painter = painterResource(Res.drawable.ic_menu),
                        contentDescription = stringResource(Res.string.drag_and_drop),
                        tint = KnightsTheme.colorScheme.trailingIconColor,
                    )
                },
            )
        }
    }
}

@Composable
private fun BookmarkEmptyScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(Res.string.empty_bookmark_item_description),
            style = KnightsTheme.typography.titleSmallM,
            color = KnightsTheme.colorScheme.onSurface,
        )
    }
}

@Preview
@Composable
private fun BookmarkScreenLightPreview() {
    val sampleSession = Session(
        id = "6",
        title = "Jetpack Compose에 있는 것, 없는것",
        content = "",
        speakers = listOf(
            Speaker(
                name = "홍길동",
                introduction = "",
                imageUrl = "",
            ),
        ),
        tags = listOf(Tag("아키텍처")),
        room = Room.TRACK1,
        startTime = LocalDateTime.parse("2025-06-17T15:35:00"),
        endTime = LocalDateTime.parse("2025-06-17T16:20:00"),
        isBookmarked = true,
    )
    KnightsTheme(darkTheme = false) {
        BookmarkScreen(
            isEditMode = true,
            bookmarkItems = persistentListOf(
                BookmarkItemUiState(
                    index = 0,
                    session = sampleSession,
                ),
            ),
            toggleEditMode = {},
            selectedSessionIds = persistentSetOf(
                sampleSession.id,
            ),
            onSelectedItem = {},
            onDeletedSessions = {},
        )
    }
}

@Preview
@Composable
private fun BookmarkScreenDarkPreview() {
    val sampleSession = Session(
        id = "6",
        title = "Jetpack Compose에 있는 것, 없는것",
        content = "",
        speakers = listOf(
            Speaker(
                name = "홍길동",
                introduction = "",
                imageUrl = "",
            ),
        ),
        tags = listOf(Tag("아키텍처")),
        room = Room.TRACK1,
        startTime = LocalDateTime.parse("2025-06-17T15:35:00"),
        endTime = LocalDateTime.parse("2025-06-17T16:20:00"),
        isBookmarked = true,
    )
    KnightsTheme(darkTheme = true) {
        BookmarkScreen(
            isEditMode = true,
            bookmarkItems = persistentListOf(
                BookmarkItemUiState(
                    index = 0,
                    session = sampleSession,
                ),
            ),
            toggleEditMode = {},
            selectedSessionIds = persistentSetOf(
                sampleSession.id,
            ),
            onSelectedItem = {},
            onDeletedSessions = {},
        )
    }
}
