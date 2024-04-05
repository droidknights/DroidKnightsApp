package com.droidknights.app.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.domain.usecase.GetSponsorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class HomeViewModel @Inject constructor(
    getSponsorsUseCase: GetSponsorsUseCase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    val sponsorsUiState: StateFlow<SponsorsUiState> = flow { emit(getSponsorsUseCase()) }
        .map { sponsors ->
            if (sponsors.isNotEmpty()) {
                SponsorsUiState.Sponsors(sponsors)
            } else {
                SponsorsUiState.Empty
            }
        }
        .catch { throwable ->
            _errorFlow.emit(throwable)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SponsorsUiState.Loading,
        )
}
