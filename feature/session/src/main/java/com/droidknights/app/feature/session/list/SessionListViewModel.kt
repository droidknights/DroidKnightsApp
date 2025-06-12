package com.droidknights.app.feature.session.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.domain.session.usecase.api.GetBookmarkedSessionIdsUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetSessionsUseCase
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.feature.session.api.RouteSessionDetail
import com.droidknights.app.feature.session.list.model.SessionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionListViewModel @Inject constructor(
    getSessionsUseCase: GetSessionsUseCase,
    getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _uiState = MutableStateFlow<SessionUiState>(SessionUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        combine(
            getSessionsUseCase(),
            getBookmarkedSessionIdsUseCase()
        ) { sessions, bookmarkedIds ->
            SessionUiState.Sessions(
                sessions = sessions
                    .map { session ->
                        session.copy(isBookmarked = bookmarkedIds.contains(session.id))
                    }
                    .toPersistentList()
            )
        }
            .catch { throwable ->
                _errorFlow.emit(throwable)
            }
            .onEach { combinedUiState ->
                _uiState.value = combinedUiState
            }
            .launchIn(viewModelScope)
    }

    fun navigateSessionDetail(sessionId: String) = viewModelScope.launch {
        navigator.navigate(RouteSessionDetail(sessionId))
    }

    fun navigateBack() = viewModelScope.launch {
        navigator.navigateBack()
    }
}
