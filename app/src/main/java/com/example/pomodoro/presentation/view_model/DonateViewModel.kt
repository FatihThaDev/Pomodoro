package com.example.pomodoro.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodoro.model.data.local.entity.DonationEntity
import com.example.pomodoro.model.repository.DonationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DonateUiState(
    val title: String = "Support My Work",
    val message: String = "Your donations help me keep creating free apps!",
    val donations: List<DonationEntity> = emptyList()
)

@HiltViewModel
class DonateViewModel @Inject constructor(
    private val donationRepository: DonationRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DonateUiState())
    val uiState: StateFlow<DonateUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch { loadDonations() }
    }

    private suspend fun loadDonations() {
        val donations = donationRepository.getAllDonations()
        _uiState.value = _uiState.value.copy(donations = donations)
    }
}
