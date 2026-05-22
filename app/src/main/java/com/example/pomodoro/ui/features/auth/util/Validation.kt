package com.example.pomodoro.ui.features.auth.util

object Validation {
    fun isValidName(name: String): Boolean = name.isNotBlank() && name.length >= 2

    fun isValidUsername(username: String): Boolean =
        username.isNotBlank() && username.length >= 3

    fun isValidEmail(email: String): Boolean =
        email.isNotBlank() && email.contains("@") && email.contains(".")

    fun isValidPassword(password: String): Boolean =
        password.isNotBlank() && password.length >= 6
}