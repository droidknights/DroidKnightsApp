package com.droidknights.app.feature.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.domain.session.api.usecase.BookmarkSessionUseCase
import com.droidknights.app.core.domain.session.api.usecase.GetBookmarkedSessionIdsUseCase
import com.droidknights.app.core.domain.session.api.usecase.GetSessionUseCase
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.feature.session.model.SessionDetailEffect
import com.droidknights.app.feature.session.model.SessionDetailUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SessionDetailViewModel(
    private val getSessionUseCase: GetSessionUseCase,
    getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase,
    private val bookmarkSessionUseCase: BookmarkSessionUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    private val _uiState = MutableStateFlow<SessionDetailUiState>(SessionDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _sessionUiEffect = MutableSharedFlow<SessionDetailEffect>()
    val sessionUiEffect = _sessionUiEffect.asSharedFlow()

    init {
        getBookmarkedSessionIdsUseCase()
            .onEach { bookmarkIds ->
                _uiState.update { currentState ->
                    if (currentState is SessionDetailUiState.Success) {
                        currentState.copy(bookmarked = bookmarkIds.contains(currentState.session.id))
                    } else {
                        currentState
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun fetchSession(sessionId: String) {
        viewModelScope.launch {
            _uiState.update { SessionDetailUiState.Loading }
            val session = getSessionUseCase(sessionId)
            _uiState.update { SessionDetailUiState.Success(session) }
        }
    }

    fun toggleBookmark() {
        val currentUiState = uiState.value
        if (currentUiState !is SessionDetailUiState.Success) {
            return
        }

        val newBookmarkState = !currentUiState.bookmarked

        _uiState.update { state ->
            if (state is SessionDetailUiState.Success) {
                state.copy(bookmarked = newBookmarkState)
            } else {
                state
            }
        }

        viewModelScope.launch {
            runCatching {
                bookmarkSessionUseCase(currentUiState.session.id, newBookmarkState)
            }.onSuccess {
                _sessionUiEffect.emit(
                    SessionDetailEffect.ShowToastForBookmarkState(newBookmarkState),
                )
            }.onFailure { throwable ->
                _uiState.update { state ->
                    if (state is SessionDetailUiState.Success) {
                        state.copy(bookmarked = !newBookmarkState)
                    } else {
                        state
                    }
                }
                // TODO: 로깅 혹은 에러 표시
            }
        }
    }

    fun navigateBack() = viewModelScope.launch {
        navigator.navigateBack()
    }
}
