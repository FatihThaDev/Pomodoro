package com.example.pomodoro.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor() {
    private val users = mutableMapOf<String, String>()

    init {
        users["test@gmail.com"] = "123456"
        users["Guest"] = ""
    }

    fun login(username: String, password: String): Boolean {
        return users[username] == password
    }

    fun register(username: String, email: String, password: String): Boolean {
        if (users.containsKey(username) || users.containsKey(email)) return false
        users[username] = password
        users[email] = password
        return true
    }
}
