package com.example.pomodoro.model.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pomodoro.model.data.local.entity.DonationEntity

@Dao
interface DonationDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(donation: DonationEntity): Long

    @Delete
    suspend fun delete(donation: DonationEntity)

    @Query("SELECT * FROM donations WHERE donationId = :donationId LIMIT 1")
    suspend fun getDonationById(donationId: Long): DonationEntity?

    @Query("SELECT * FROM donations WHERE userId = :userId ORDER BY createdAt DESC")
    suspend fun getDonationsByUserId(userId: Long): List<DonationEntity>

    @Query("SELECT * FROM donations ORDER BY createdAt DESC")
    suspend fun getAllDonations(): List<DonationEntity>
}
