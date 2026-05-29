package com.example.pomodoro.data.session

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSession @Inject constructor() {
    var currentUserId: Long = 1L
}