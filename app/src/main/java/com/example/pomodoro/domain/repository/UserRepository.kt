package com.example.pomodoro.domain.repository

import com.example.pomodoro.domain.model.User

interface UserRepository {
    suspend fun register(username: String, email: String, password: String): User
    suspend fun login(username: String, password: String): User?
    suspend fun getUserById(userId: Long): User?
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
}
