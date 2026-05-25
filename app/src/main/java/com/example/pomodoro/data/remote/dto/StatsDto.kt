package com.example.pomodoro.data.remote.dto

import com.google.gson.annotations.SerializedName

data class StatsDto(
    @SerializedName("total_sessions")
    val totalSessions: Int = 0,
    @SerializedName("total_focus_minutes")
    val totalFocusMinutes: Int = 0
)
