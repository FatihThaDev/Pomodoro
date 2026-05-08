package com.example.pomodoro.presentation.view_model

import androidx.lifecycle.ViewModel
import com.example.pomodoro.model.DonateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class DonateUiState(
    val title: String = "Support My Work",
    val message: String = ""
)

@HiltViewModel
class DonateViewModel @Inject constructor(
    private val donateRepository: DonateRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DonateUiState(message = donateRepository.getDonationMessage()))
    val uiState: StateFlow<DonateUiState> = _uiState.asStateFlow()
}
