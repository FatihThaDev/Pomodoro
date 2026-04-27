package com.example.pomodoro.presentation.view_model

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class DonateUiState(
    val title: String = "Support My Work",
    val message: String = "Your donations help me keep creating free apps!"
)

@HiltViewModel
class DonateViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DonateUiState())
    val uiState: StateFlow<DonateUiState> = _uiState.asStateFlow()
}