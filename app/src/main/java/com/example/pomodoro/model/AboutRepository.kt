package com.example.pomodoro.model

import javax.inject.Inject

class AboutRepository @Inject constructor() {
    fun getAppVersion(): String = "1.0.0"
}