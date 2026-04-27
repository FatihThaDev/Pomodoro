package com.example.pomodoro.presentation.view_model

import androidx.lifecycle.ViewModel
import com.example.pomodoro.presentation.ui.screens.dashboard.util.SessionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class DashboardUiState(
    val username: String = "",
    val minutes: Int = 25,
    val seconds: Int = 0,
    val isRunning: Boolean = false,
    val isTimerFinished: Boolean = false,
    val sessionData: SessionData = SessionData()
)

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    fun startTimer() {
        _uiState.value = _uiState.value.copy(isRunning = true)
    }

    fun pauseTimer() {
        _uiState.value = _uiState.value.copy(isRunning = false)
    }

    fun resetTimer() {
        _uiState.value = _uiState.value.copy(
            minutes = 25,
            seconds = 0,
            isRunning = false,
            isTimerFinished = false
        )
    }

    fun setUsername(name: String) {
        _uiState.value = _uiState.value.copy(username = name)
    }
}