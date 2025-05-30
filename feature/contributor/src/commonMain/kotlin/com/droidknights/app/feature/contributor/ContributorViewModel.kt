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
        _uiState.value = ContributorsUiState.Contributors.DummyData
    }
}
