package com.example.pomodoro.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SessionDto(
    val id: Long = 0,
    @SerializedName("user_id")
    val userId: Long,
    @SerializedName("focus_minutes")
    val focusMinutes: Int,
    @SerializedName("completed_at")
    val completedAt: String = ""
)
