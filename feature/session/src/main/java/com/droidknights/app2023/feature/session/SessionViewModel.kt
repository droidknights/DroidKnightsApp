package com.droidknights.app2023.feature.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app2023.core.domain.usecase.GetBookmarkedSessionIdsUseCase
import com.droidknights.app2023.core.domain.usecase.GetSessionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val getSessionsUseCase: GetSessionsUseCase,
    private val getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    val uiState: StateFlow<SessionUiState> = flow {
        emit(getSessionsUseCase().toPersistentList() to getBookmarkedSessionIdsUseCase().first())
    }.map { (sessions, bookmarkedIds) ->
        val enhancedSessions = sessions.map { session ->
            session.copy(isBookmarked = bookmarkedIds.contains(session.id))
        }
        SessionUiState.Sessions(
            sessions = enhancedSessions.toPersistentList()
        )
    }.catch { throwable ->
        _errorFlow.emit(throwable)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SessionUiState.Loading
    )
}
