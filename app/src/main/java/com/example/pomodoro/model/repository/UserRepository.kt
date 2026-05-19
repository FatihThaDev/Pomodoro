package com.example.pomodoro.model.repository

import com.example.pomodoro.model.data.local.entity.UserEntity

interface UserRepository {
    suspend fun register(username: String, email: String, password: String): Result<UserEntity>
    suspend fun login(username: String, password: String): Result<UserEntity>
    suspend fun getUserById(userId: Long): UserEntity?
    suspend fun updateUser(user: UserEntity)
    suspend fun deleteUser(user: UserEntity)
}
