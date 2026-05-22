package com.example.pomodoro.domain.repository

import com.example.pomodoro.domain.model.Donation

interface DonationRepository {
    suspend fun createDonation(userId: Long, amount: Double, message: String): Long
    suspend fun getDonationsByUserId(userId: Long): List<Donation>
    suspend fun getAllDonations(): List<Donation>
}
