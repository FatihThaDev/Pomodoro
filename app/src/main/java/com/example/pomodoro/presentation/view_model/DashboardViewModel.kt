package com.example.pomodoro.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodoro.model.di.UserSession
import com.example.pomodoro.model.repository.SessionRepository
import com.example.pomodoro.presentation.ui.screens.dashboard.util.SessionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
class DashboardViewModel @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val userSession: UserSession
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    fun startTimer() {
        _uiState.value = _uiState.value.copy(isRunning = true)
        viewModelScope.launch {
            sessionRepository.createSession(userId = userSession.currentUserId, focusMinutes = _uiState.value.minutes)
            loadSessionData()
        }
    }

    fun pauseTimer() {
        _uiState.value = _uiState.value.copy(isRunning = false)
    }

    fun resetTimer() {
        _uiState.value = _uiState.value.copy(
            minutes = 0,
            seconds = 0,
            isRunning = false,
            isTimerFinished = false
        )
    }

    fun setUsername(name: String) {
        _uiState.value = _uiState.value.copy(username = name)
    }

    private suspend fun loadSessionData() {
        val sessions = sessionRepository.getSessionsByUserId(userSession.currentUserId)
        _uiState.value = _uiState.value.copy(
            sessionData = SessionData(
                sessionsCompleted = sessions.size,
                dailySessions = sessions.size,
                streak = sessions.size
            )
        )
    }
}
