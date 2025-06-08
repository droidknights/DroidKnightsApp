package com.droidknights.app.feature.contributor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.domain.contributor.api.GetContributorsUseCase
import com.droidknights.app.core.model.contributor.ContributorGroup
import com.droidknights.app.feature.contributor.model.ContributorsUiState
import com.droidknights.app.feature.contributor.model.convert.toContributorsUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

internal class ContributorViewModel(
    private val getContributorsUseCase: GetContributorsUseCase,
) : ViewModel() {

    // TODO 스낵바 연결
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _uiState = MutableStateFlow<ContributorsUiState>(ContributorsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchContributors()
    }

    private fun fetchContributors() {
        getContributorsUseCase()
            .map { contributorGroups: List<ContributorGroup> ->
                contributorGroups.toContributorsUiState()
            }
            .catch { throwable ->
                _errorFlow.emit(throwable)
            }
            .onEach { contributorsUiState ->
                _uiState.value = contributorsUiState
            }
            .launchIn(viewModelScope)
    }
}
