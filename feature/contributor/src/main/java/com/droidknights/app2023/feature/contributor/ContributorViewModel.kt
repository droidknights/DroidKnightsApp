package com.droidknights.app2023.feature.contributor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app2023.core.domain.usecase.GetContributorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ContributorViewModel @Inject constructor(
    getContributorsUseCase: GetContributorsUseCase,
) : ViewModel() {

    private val errorStateChannel = Channel<ContributorsUiState.Error>()
    val errorStateFlow get() = errorStateChannel.receiveAsFlow()

    val uiState: StateFlow<ContributorsUiState> =
        flow { emit(getContributorsUseCase().toPersistentList()) }
            .map(ContributorsUiState::Contributors)
            .catch { throwable ->
                errorStateChannel.send(ContributorsUiState.Error(throwable))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ContributorsUiState.Loading,
            )
}
