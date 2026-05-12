package com.example.pomodoro.model.repository.impl

import com.example.pomodoro.model.data.local.dao.DonationDao
import com.example.pomodoro.model.data.local.entity.DonationEntity
import com.example.pomodoro.model.repository.DonationRepository
import javax.inject.Inject

class DonationRepositoryImpl @Inject constructor(
    private val donationDao: DonationDao
) : DonationRepository {

    override suspend fun createDonation(userId: Long, amount: Double, message: String): Long {
        return donationDao.insert(DonationEntity(userId = userId, amount = amount, message = message))
    }

    override suspend fun getDonationsByUserId(userId: Long): List<DonationEntity> {
        return donationDao.getDonationsByUserId(userId)
    }

    override suspend fun getAllDonations(): List<DonationEntity> {
        return donationDao.getAllDonations()
    }
}
