package com.example.pomodoro.presentation.ui.util

import android.util.Patterns

object Validation {
    fun isValidName(name: String): Boolean {
        return name.length >= 2 && name.isNotEmpty()
    }

    fun isValidUsername(username: String): Boolean {
        return username.length >= 3 && username.isNotEmpty()
    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 6 && password.isNotEmpty()
    }
}
