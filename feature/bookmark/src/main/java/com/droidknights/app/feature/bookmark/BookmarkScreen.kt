package com.droidknights.app.feature.bookmark

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.feature.bookmark.component.BookmarkCard
import com.droidknights.app.feature.bookmark.component.BookmarkItem
import com.droidknights.app.feature.bookmark.component.BookmarkTimelineItem
import com.droidknights.app.feature.bookmark.component.BookmarkTopAppBar
import com.droidknights.app.feature.bookmark.component.EditModeLeadingItem
import com.droidknights.app.feature.bookmark.component.RemoveBookmarkSnackBar
import com.droidknights.app.feature.bookmark.model.BookmarkItemUiState
import com.droidknights.app.feature.bookmark.model.BookmarkUiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun BookmarkRoute(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val bookmarkUiState by viewModel.bookmarkUiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { onShowErrorSnackBar(it) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
            .systemBarsPadding()
    ) {
        BookmarkContent(
            uiState = bookmarkUiState,
            onClickedRedirectItem = viewModel::redirectToSessionScreen,
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
    onClickedRedirectItem: (Session) -> Unit,
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
            onClickedRedirectItem = onClickedRedirectItem,
            onDeletedSessions = onDeletedSessions,
        )
    }
}

@Composable
private fun BookmarkLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun BookmarkScreen(
    isEditMode: Boolean,
    bookmarkItems: ImmutableList<BookmarkItemUiState>,
    toggleEditMode: () -> Unit,
    selectedSessionIds: ImmutableSet<String>,
    onSelectedItem: (Session) -> Unit,
    onClickedRedirectItem: (Session) -> Unit,
    onDeletedSessions: () -> Unit,
    listContentBottomPadding: Dp = 72.dp,
) {
    BackHandler(isEditMode) {
        toggleEditMode()
    }

    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surfaceDim),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            BookmarkTopAppBar(
                isEditMode = isEditMode,
                onClickEditButton = toggleEditMode
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
                onClickedRedirectItem = onClickedRedirectItem,
            )
        }

        AnimatedVisibility(
            visible = selectedSessionIds.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            RemoveBookmarkSnackBar(
                modifier = Modifier
                    .padding(bottom = 92.dp)
                    .padding(horizontal = 8.dp),
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
    onClickedRedirectItem: (Session) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = listContentBottomPadding)
    ) {
        items(
            items = bookmarkItems,
            key = { item -> item.session.id }
        ) { itemState ->
            val isSelected = selectedSessionIds.contains(itemState.session.id)
            BookmarkItem(
                modifier = Modifier
                    .background(
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.surfaceContainerHigh
                        } else {
                            MaterialTheme.colorScheme.surfaceDim
                        }
                    )
                    .padding(
                        end = if (isEditMode) 0.dp else 16.dp
                    )
                    .clickable(enabled = isEditMode.not()) {
                        onClickedRedirectItem(itemState.session)
                    },
                leadingContent = @Composable {
                    if (isEditMode) {
                        EditModeLeadingItem(
                            itemState = itemState,
                            selectedSessionIds = selectedSessionIds,
                            onSelectedItem = onSelectedItem
                        )
                    } else {
                        BookmarkTimelineItem(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            sequence = itemState.sequence,
                            time = itemState.time
                        )
                    }
                },
                midContent = @Composable {
                    BookmarkCard(
                        tagLabel = itemState.tagLabel,
                        room = itemState.session.room,
                        title = itemState.session.title,
                        speaker = itemState.speakerLabel
                    )
                },
                isEditMode = isEditMode,
                trailingContent = @Composable {
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 18.dp)
                            .size(24.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu),
                        contentDescription = stringResource(id = R.string.drag_and_drop),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )
        }
    }
}

@Composable
private fun BookmarkEmptyScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.empty_bookmark_item_description),
            style = KnightsTheme.typography.titleSmallM,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
