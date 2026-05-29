package com.example.pomodoro.ui.features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodoro.data.session.UserSession
import com.example.pomodoro.domain.repository.SessionRepository
import com.example.pomodoro.ui.features.dashboard.util.SessionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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

    private var timerJob: Job? = null

    init {
        viewModelScope.launch { loadSessionData() }
    }

    fun startTimer() {
        if (_uiState.value.isRunning) return
        val focusMinutes = _uiState.value.minutes
        _uiState.value = _uiState.value.copy(isRunning = true, isTimerFinished = false)

        timerJob = viewModelScope.launch {
            var remainingSeconds = focusMinutes * 60 + _uiState.value.seconds
            while (remainingSeconds > 0) {
                delay(1000)
                remainingSeconds--
                val mins = remainingSeconds / 60
                val secs = remainingSeconds % 60
                _uiState.value = _uiState.value.copy(minutes = mins, seconds = secs)
            }
            _uiState.value = _uiState.value.copy(isRunning = false, isTimerFinished = true)
            sessionRepository.createSession(userId = userSession.currentUserId, focusMinutes = focusMinutes)
            loadSessionData()
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
        _uiState.value = _uiState.value.copy(isRunning = false)
    }

    fun resetTimer() {
        timerJob?.cancel()
        _uiState.value = _uiState.value.copy(
            minutes = 25,
            seconds = 0,
            isRunning = false,
            isTimerFinished = false
        )
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
