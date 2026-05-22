package com.example.pomodoro.data.mapper

import com.example.pomodoro.data.local.entity.DonationEntity
import com.example.pomodoro.domain.model.Donation

fun DonationEntity.toDomain(): Donation = Donation(
    donationId = donationId,
    userId = userId,
    amount = amount,
    message = message,
    createdAt = createdAt
)

fun Donation.toEntity(): DonationEntity = DonationEntity(
    donationId = donationId,
    userId = userId,
    amount = amount,
    message = message,
    createdAt = createdAt
)
