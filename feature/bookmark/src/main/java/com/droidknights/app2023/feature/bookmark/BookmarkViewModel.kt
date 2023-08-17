package com.droidknights.app2023.feature.bookmark

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class BookmarkViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState: MutableStateFlow<BookmarkUiState> = MutableStateFlow(BookmarkUiState())
    val uiState: StateFlow<BookmarkUiState>
        get() = _uiState

    fun clickEditButton() {
        _uiState.value = _uiState.value.copy(isEditMode = _uiState.value.isEditMode.not())
    }

}