package com.example.pomodoro.model

import javax.inject.Inject

class DonateRepository @Inject constructor() {
    fun getDonationMessage(): String = "Thank you for your support!"
}