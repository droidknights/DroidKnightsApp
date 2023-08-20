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
                                    BookmarkItemUiState(
                                        index = index,
                                        session = session,
                                        isEditMode = bookmarkUiState.isEditButtonSelected
                                    )
                                }
                        )
                    }
                }
            }.collect { _bookmarkUiState.value = it }
        }
    }

    fun clickEditButton() {
        val state = _bookmarkUiState.value
        if (state !is BookmarkUiState.Success) {
            return
        }

        _bookmarkUiState.value = state.copy(
            isEditButtonSelected = state.isEditButtonSelected.not(),
            bookmarks = state.bookmarks.map { it.copy(isEditMode = !it.isEditMode.not()) }
        )
    }
}
