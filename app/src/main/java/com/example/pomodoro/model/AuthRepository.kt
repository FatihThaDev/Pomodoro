package com.example.pomodoro.model

import javax.inject.Inject

class AuthRepository @Inject constructor() {

    fun login(email: String, password: String): Boolean {
        return email == "test@gmail.com" && password == "123456"
    }
}