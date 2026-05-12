package com.example.pomodoro.model.repository.impl

import com.example.pomodoro.model.data.local.dao.UserDao
import com.example.pomodoro.model.data.local.entity.UserEntity
import com.example.pomodoro.model.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun register(username: String, email: String, password: String): Result<UserEntity> {
        val existing = userDao.getUserByUsername(username)
        if (existing != null) return Result.failure(Exception("Username already exists"))
        val id = userDao.insert(UserEntity(username = username, email = email, password = password))
        return Result.success(UserEntity(userId = id, username = username, email = email, password = password))
    }

    override suspend fun login(username: String, password: String): Result<UserEntity> {
        val user = userDao.getUserByUsername(username)
        return if (user != null && user.password == password) {
            Result.success(user)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    override suspend fun getUserById(userId: Long): UserEntity? = userDao.getUserById(userId)

    override suspend fun updateUser(user: UserEntity) = userDao.update(user)

    override suspend fun deleteUser(user: UserEntity) = userDao.delete(user)
}
