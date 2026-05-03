package com.example.pomodoro.model

import javax.inject.Inject

class AuthRepository @Inject constructor() {

    fun login(username: String, password: String): Boolean {
        return username == "test@gmail.com" && password == "123456"
    }
}