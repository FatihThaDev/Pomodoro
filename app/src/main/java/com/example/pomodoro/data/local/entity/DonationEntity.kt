package com.example.pomodoro.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "donations",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["userId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("userId")]
)
data class DonationEntity(
    @PrimaryKey(autoGenerate = true) val donationId: Long = 0,
    val userId: Long,
    val amount: Double,
    val message: String,
    val createdAt: Long = System.currentTimeMillis()
)
