package com.droidknights.app2023.feature.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app2023.core.domain.usecase.GetBookmarkedSessionIdsUseCase
import com.droidknights.app2023.core.domain.usecase.GetSessionsUseCase
import com.droidknights.app2023.core.model.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.datetime.toJavaLocalDateTime
import javax.inject.Inject


@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getSessionsUseCase: GetSessionsUseCase,
    private val getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase
) : ViewModel() {

    private val sessions = MutableStateFlow<List<Session>>(emptyList())

    private val _bookmarkUiState = MutableStateFlow<BookmarkUiState>(BookmarkUiState.Loading)
    val bookmarkUiState: StateFlow<BookmarkUiState> = _bookmarkUiState

    init {
        viewModelScope.launch {
            sessions.value = getSessionsUseCase()
            _bookmarkUiState.value = BookmarkUiState.Success()

            combine(
                bookmarkUiState,
                getBookmarkedSessionIdsUseCase()
            ) { bookmarkUiState, bookmarkIds ->
                when (bookmarkUiState) {
                    is BookmarkUiState.Loading -> bookmarkUiState
                    is BookmarkUiState.Success -> {
                        bookmarkUiState.copy(
                            bookmarks = sessions.value
                                .filter { bookmarkIds.contains(it.id) }
                                .sortedBy { it.startTime }
                                .mapIndexed { index, session ->
                                    toBookmarkItemState(index, session, bookmarkUiState.isEditMode)
                                }
                        )
                    }
                }
            }.collect { _bookmarkUiState.value = it }
        }
    }

    fun clickEditButton() {
        bookmarkUiState.value.ifStateIsSuccess { state ->
            _bookmarkUiState.value = state.copy(
                isEditMode = state.isEditMode.not(),
                bookmarks = state.bookmarks.map { it.copy(isEditMode = state.isEditMode.not()) }
            )
        }
    }

}

private fun toBookmarkItemState(index: Int, session: Session, isEditMode: Boolean): BookmarkItemState {
    return BookmarkItemState(
        sessionId = session.id,
        sequence = index + 1,
        title = session.title,
        tagLabel = session.tags.joinToString { it.name },
        room = session.room,
        speakerLabel = session.speakers.joinToString { it.name },
        time = session.startTime.toJavaLocalDateTime().toLocalTime(),
        isEditMode = isEditMode
    )
}