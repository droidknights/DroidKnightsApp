package com.droidknights.app.feature.bookmark.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
sealed interface BookmarkUiState {

    @Immutable
    data object Loading : BookmarkUiState

    @Immutable
    data class Success(
        val isEditButtonSelected: Boolean = false,
        val bookmarks: ImmutableList<BookmarkItemUiState> = persistentListOf(),
        val selectedSessionIds: ImmutableList<String> = persistentListOf(),
    ) : BookmarkUiState
}
