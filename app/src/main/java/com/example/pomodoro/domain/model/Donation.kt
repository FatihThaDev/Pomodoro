package com.example.pomodoro.domain.model

data class Donation(
    val donationId: Long = 0,
    val userId: Long,
    val amount: Double,
    val message: String,
    val createdAt: Long = System.currentTimeMillis()
)
