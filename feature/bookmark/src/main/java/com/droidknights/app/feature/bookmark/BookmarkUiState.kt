package com.droidknights.app.feature.bookmark

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface BookmarkUiState {

    @Immutable
    data object Loading : BookmarkUiState

    @Immutable
    data class Success(
        val isEditButtonSelected: Boolean = false,
        val bookmarks: List<BookmarkItemUiState> = listOf(),
    ) : BookmarkUiState
}
