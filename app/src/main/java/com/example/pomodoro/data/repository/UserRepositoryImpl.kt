package com.example.pomodoro.data.repository

import com.example.pomodoro.data.local.dao.UserDao
import com.example.pomodoro.data.local.entity.UserEntity
import com.example.pomodoro.data.mapper.toDomain
import com.example.pomodoro.data.mapper.toEntity
import com.example.pomodoro.domain.model.User
import com.example.pomodoro.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun register(username: String, email: String, password: String): User {
        require(userDao.getUserByUsername(username) == null) { "Username already exists" }
        val id = userDao.insert(UserEntity(username = username, email = email, password = password))
        return User(userId = id, username = username, email = email, password = password)
    }

    override suspend fun login(username: String, password: String): User? {
        val user = userDao.getUserByUsername(username) ?: return null
        if (user.password != password) return null
        return user.toDomain()
    }

    override suspend fun getUserById(userId: Long): User? = userDao.getUserById(userId)?.toDomain()

    override suspend fun updateUser(user: User) = userDao.update(user.toEntity())

    override suspend fun deleteUser(user: User) = userDao.delete(user.toEntity())
}
