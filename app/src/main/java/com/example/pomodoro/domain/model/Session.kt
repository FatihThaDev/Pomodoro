package com.example.pomodoro.domain.model

data class Session(
    val sessionId: Long = 0,
    val userId: Long,
    val focusMinutes: Int,
    val completedAt: Long = System.currentTimeMillis()
)
