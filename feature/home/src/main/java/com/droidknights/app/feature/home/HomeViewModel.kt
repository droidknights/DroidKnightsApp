package com.droidknights.app.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.domain.sponsor.usecase.api.GetSponsorsUseCase
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.feature.contributor.api.RouteContributor
import com.droidknights.app.feature.home.model.SponsorsUiState
import com.droidknights.app.feature.session.api.RouteSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getSponsorsUseCase: GetSponsorsUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow get() = _errorFlow.asSharedFlow()

    val sponsorsUiState: StateFlow<SponsorsUiState> =
        flow { emit(getSponsorsUseCase()) }
            .map { sponsors ->
                if (sponsors.isNotEmpty()) {
                    SponsorsUiState.Sponsors(sponsors.toPersistentList())
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

    fun navigateSession() = viewModelScope.launch {
        navigator.navigate(RouteSession())
    }

    fun navigateContributor() = viewModelScope.launch {
        navigator.navigate(RouteContributor)
    }

    fun navigateOrganizationSponsor(url: String) = viewModelScope.launch {
        navigator.navigateWeb(url)
    }
}
