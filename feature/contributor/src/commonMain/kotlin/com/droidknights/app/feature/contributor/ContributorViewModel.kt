package com.droidknights.app.feature.contributor

import androidx.lifecycle.ViewModel
import com.droidknights.app.feature.contributor.model.ContributorsUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

internal class ContributorViewModel : ViewModel() {
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _uiState = MutableStateFlow<ContributorsUiState>(ContributorsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadContributor()
    }

    private fun loadContributor() {
        // TODO: core:network가 아직 구현되지 않아 임시 데이터를 사용했습니다. 구현 후 실제 데이터로 교체해야 합니다.
        _uiState.value = ContributorsUiState.Contributors.DummyData
    }
}
