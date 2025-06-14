package com.droidknights.app.feature.session.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.domain.session.usecase.api.BookmarkSessionUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetBookmarkedSessionIdsUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetSessionUseCase
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.feature.session.detail.model.SessionDetailEffect
import com.droidknights.app.feature.session.detail.model.SessionDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionDetailViewModel @Inject constructor(
    private val getSessionUseCase: GetSessionUseCase,
    getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase,
    private val bookmarkSessionUseCase: BookmarkSessionUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    private val _sessionUiState =
        MutableStateFlow<SessionDetailUiState>(SessionDetailUiState.Loading)
    val sessionUiState = _sessionUiState.asStateFlow()

    private val _sessionUiEffect = MutableStateFlow<SessionDetailEffect>(SessionDetailEffect.Idle)
    val sessionUiEffect = _sessionUiEffect.asStateFlow()

    init {
        combine(
            sessionUiState,
            getBookmarkedSessionIdsUseCase(),
        ) { sessionUiState, bookmarkIds ->
            when (sessionUiState) {
                is SessionDetailUiState.Loading -> sessionUiState
                is SessionDetailUiState.Success -> {
                    sessionUiState.copy(bookmarked = bookmarkIds.contains(sessionUiState.session.id))
                }
            }
        }
            .onEach { _sessionUiState.value = it }
            .launchIn(viewModelScope)
    }

    fun fetchSession(sessionId: String) {
        viewModelScope.launch {
            val session = getSessionUseCase(sessionId)
            _sessionUiState.value = SessionDetailUiState.Success(session)
        }
    }

    fun toggleBookmark() {
        val uiState = sessionUiState.value
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

    fun navigateBack() = viewModelScope.launch {
        navigator.navigateBack()
    }
}
