package com.example.pomodoro.data.repository

import com.example.pomodoro.data.local.dao.DonationDao
import com.example.pomodoro.data.local.entity.DonationEntity
import com.example.pomodoro.data.mapper.toDomain
import com.example.pomodoro.domain.model.Donation
import com.example.pomodoro.domain.repository.DonationRepository
import javax.inject.Inject

class DonationRepositoryImpl @Inject constructor(
    private val donationDao: DonationDao
) : DonationRepository {

    override suspend fun createDonation(userId: Long, amount: Double, message: String): Long {
        return donationDao.insert(DonationEntity(userId = userId, amount = amount, message = message))
    }

    override suspend fun getDonationsByUserId(userId: Long): List<Donation> {
        return donationDao.getDonationsByUserId(userId).map { it.toDomain() }
    }

    override suspend fun getAllDonations(): List<Donation> {
        return donationDao.getAllDonations().map { it.toDomain() }
    }
}
