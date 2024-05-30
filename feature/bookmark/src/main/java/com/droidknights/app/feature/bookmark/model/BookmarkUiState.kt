package com.droidknights.app.feature.bookmark.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

@Stable
sealed interface BookmarkUiState {

    @Immutable
    data object Loading : BookmarkUiState

    @Immutable
    data class Success(
        val isEditMode: Boolean = false,
        val bookmarks: ImmutableList<BookmarkItemUiState> = persistentListOf(),
        val selectedSessionIds: ImmutableSet<String> = persistentSetOf(),
    ) : BookmarkUiState
}
