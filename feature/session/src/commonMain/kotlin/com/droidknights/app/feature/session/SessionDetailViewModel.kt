package com.droidknights.app.feature.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.domain.session.api.usecase.GetSessionUseCase
import com.droidknights.app.feature.session.model.SessionDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class SessionDetailViewModel(
    private val getSessionUseCase: GetSessionUseCase,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<SessionDetailUiState>(SessionDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchSession(sessionId: String) {
        viewModelScope.launch {
            val session = getSessionUseCase(sessionId)
            _uiState.value = SessionDetailUiState.Success(session)
        }
    }

    // TODO 북마크
}
