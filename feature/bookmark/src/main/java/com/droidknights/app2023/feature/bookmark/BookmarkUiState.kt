package com.droidknights.app2023.feature.bookmark

sealed interface BookmarkUiState {
    object Loading : BookmarkUiState
    data class Success(val isEditButtonSelected: Boolean = false, val bookmarks: List<BookmarkItemUiState> = listOf()) : BookmarkUiState
}
