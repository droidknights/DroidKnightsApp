package com.droidknights.app2023.feature.contributor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app2023.core.domain.usecase.GetContributorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ContributorViewModel @Inject constructor(
    getContributorsUseCase: GetContributorsUseCase,
) : ViewModel() {

    val uiState: StateFlow<ContributorsUiState> =
        flow { emit(getContributorsUseCase()) }.map(
            ContributorsUiState::Contributors,
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ContributorsUiState.Loading,
        )
}
