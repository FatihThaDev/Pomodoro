package com.example.pomodoro.domain.model

data class User(
    val userId: Long = 0,
    val username: String,
    val email: String,
    val password: String
)
