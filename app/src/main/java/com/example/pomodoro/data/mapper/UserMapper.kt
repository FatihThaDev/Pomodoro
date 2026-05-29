package com.example.pomodoro.data.mapper

import com.example.pomodoro.data.local.entity.UserEntity
import com.example.pomodoro.domain.model.User

fun UserEntity.toDomain(): User = User(
    userId = userId,
    username = username,
    email = email,
    password = password
)

fun User.toEntity(): UserEntity = UserEntity(
    userId = userId,
    username = username,
    email = email,
    password = password
)
