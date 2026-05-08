package com.example.pomodoro.model

import com.example.pomodoro.presentation.ui.screens.dashboard.util.SessionData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepository @Inject constructor() {
    private var totalSessions = 0
    private var totalFocusSeconds = 0
    private var currentStreak = 0

    fun saveSession(focusMinutes: Int): SessionData {
        totalSessions++
        totalFocusSeconds += focusMinutes * 60
        currentStreak++
        return SessionData(
            sessionsCompleted = totalSessions,
            focusTime = totalFocusSeconds,
            dailySessions = totalSessions,
            streak = currentStreak
        )
    }
}
