package com.droidknights.app.feature.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.domain.session.api.usecase.BookmarkSessionUseCase
import com.droidknights.app.core.domain.session.api.usecase.GetBookmarkedSessionIdsUseCase
import com.droidknights.app.core.domain.session.api.usecase.GetSessionUseCase
import com.droidknights.app.feature.session.model.SessionDetailEffect
import com.droidknights.app.feature.session.model.SessionDetailUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class SessionDetailViewModel(
    private val getSessionUseCase: GetSessionUseCase,
    getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase,
    private val bookmarkSessionUseCase: BookmarkSessionUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<SessionDetailUiState>(SessionDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _sessionUiEffect = MutableStateFlow<SessionDetailEffect>(SessionDetailEffect.Idle)
    val sessionUiEffect = _sessionUiEffect.asStateFlow()

    init {
        combine(
            uiState,
            getBookmarkedSessionIdsUseCase(),
        ) { uiState, bookmarkIds ->
            when (uiState) {
                is SessionDetailUiState.Loading -> uiState
                is SessionDetailUiState.Success -> {
                    uiState.copy(bookmarked = bookmarkIds.contains(uiState.session.id))
                }
            }
        }
            .onEach { _uiState.value = it }
            .launchIn(viewModelScope)
    }

    fun fetchSession(sessionId: String) {
        viewModelScope.launch {
            _uiState.value = SessionDetailUiState.Loading
            delay(1000L)
            val session = getSessionUseCase(sessionId)
            _uiState.value = SessionDetailUiState.Success(session)
        }
    }

    fun toggleBookmark() {
        val uiState = uiState.value
        if (uiState !is SessionDetailUiState.Success) {
            return
        }
        viewModelScope.launch {
            val bookmark = uiState.bookmarked
            bookmarkSessionUseCase(uiState.session.id, !bookmark)
            _sessionUiEffect.value = SessionDetailEffect.ShowToastForBookmarkState(!bookmark)
        }
    }

    fun hidePopup() {
        viewModelScope.launch {
            _sessionUiEffect.value = SessionDetailEffect.Idle
        }
    }
}
