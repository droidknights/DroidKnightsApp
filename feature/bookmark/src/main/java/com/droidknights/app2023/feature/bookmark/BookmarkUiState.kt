package com.droidknights.app2023.feature.bookmark

import com.droidknights.app2023.core.model.Room
import java.time.LocalTime

sealed interface BookmarkUiState {
    object Loading : BookmarkUiState

    data class Success(val isEditMode: Boolean = false, val bookmarks: List<BookmarkItemUiState> = listOf()) : BookmarkUiState

    fun ifStateIsSuccess(func: (Success) -> Unit) {
        if (this is Success) {
            func(this)
        } else {
            return
        }
    }
}

data class BookmarkItemUiState(
    val sessionId: String,
    val sequence: Int,
    val title: String,
    val tagLabel: String,
    val room: Room,
    val speakerLabel: String,
    val time: LocalTime,
    val isEditMode: Boolean
)
