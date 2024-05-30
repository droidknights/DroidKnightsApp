package com.droidknights.app.feature.bookmark

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.theme.DarkGray
import com.droidknights.app.core.designsystem.theme.DuskGray
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LightGray
import com.droidknights.app.core.designsystem.theme.PaleGray
import com.droidknights.app.core.designsystem.theme.Purple01
import com.droidknights.app.core.designsystem.theme.White
import com.droidknights.app.core.model.Session
import com.droidknights.app.feature.bookmark.component.BookmarkCard
import com.droidknights.app.feature.bookmark.component.BookmarkItem
import com.droidknights.app.feature.bookmark.component.BookmarkTimelineItem
import com.droidknights.app.feature.bookmark.model.BookmarkItemUiState
import com.droidknights.app.feature.bookmark.model.BookmarkUiState
import kotlinx.collections.immutable.ImmutableList
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
            onClickEditButton = { viewModel.clickEditButton() },
            onSelectedItem = viewModel::selectSession,
        )
    }
}

@Composable
private fun BookmarkContent(
    uiState: BookmarkUiState,
    onClickEditButton: () -> Unit,
    onSelectedItem: (Session) -> Unit,
) {
    when (uiState) {
        BookmarkUiState.Loading -> BookmarkLoading()
        is BookmarkUiState.Success -> BookmarkScreen(
            isEditMode = uiState.isEditButtonSelected,
            bookmarkItems = uiState.bookmarks.toImmutableList(),
            onClickEditButton = onClickEditButton,
            selectedSessionIds = uiState.selectedSessionIds,
            onSelectedItem = onSelectedItem
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
    onClickEditButton: () -> Unit,
    selectedSessionIds: ImmutableList<String>,
    onSelectedItem: (Session) -> Unit,
    listContentBottomPadding: Dp = 72.dp,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = PaleGray),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        BookmarkTopAppBar(isEditMode = isEditMode, onClickEditButton = onClickEditButton)

        if (bookmarkItems.isEmpty()) {
            BookmarkEmptyScreen()
        }

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
                            color = if (isSelected) LightGray else PaleGray
                        )
                        .padding(
                            end = if (isEditMode) 0.dp else 16.dp
                        ),
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
                            contentDescription = stringResource(id = R.string.drag_and_drop)
                        )
                    }
                )
            }
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
            color = DuskGray
        )
    }
}

@Composable
private fun BookmarkTopAppBar(
    isEditMode: Boolean,
    onClickEditButton: () -> Unit,
) {
    val editButtonColor by animateColorAsState(
        label = "Edit Button Color Animation",
        targetValue = if (isEditMode) {
            Purple01
        } else {
            DuskGray
        }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.book_mark_top_bar_title),
            style = KnightsTheme.typography.titleSmallM,
            color = DuskGray
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable(onClick = onClickEditButton)
                .padding(horizontal = 12.dp),
            text = if (isEditMode) {
                stringResource(id = R.string.edit_button_confirm_label)
            } else {
                stringResource(id = R.string.edit_button_edit_label)
            },
            style = KnightsTheme.typography.titleSmallM,
            color = editButtonColor
        )
    }
}

@Composable
private fun EditModeLeadingItem(
    itemState: BookmarkItemUiState,
    selectedSessionIds: ImmutableList<String>,
    onSelectedItem: (Session) -> Unit,
) {
    val isSelectedItem = selectedSessionIds.contains(itemState.session.id)
    val baseModifier = Modifier
        .padding(horizontal = 18.dp)
        .size(24.dp)
        .clip(CircleShape)
        .clickable { onSelectedItem(itemState.session) }
    if (isSelectedItem) {
        Box(
            modifier = baseModifier.background(Purple01),
            contentAlignment = Alignment.Center

        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_check),
                contentDescription = null,
                tint = White
            )
        }
    } else {
        Box(
            modifier = baseModifier
                .border(
                    width = 1.dp,
                    color = DarkGray,
                    shape = CircleShape
                )
        )
    }
}
