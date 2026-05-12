package com.example.pomodoro.model.repository

import com.example.pomodoro.model.data.local.entity.DonationEntity

interface DonationRepository {
    suspend fun createDonation(userId: Long, amount: Double, message: String): Long
    suspend fun getDonationsByUserId(userId: Long): List<DonationEntity>
    suspend fun getAllDonations(): List<DonationEntity>
}
