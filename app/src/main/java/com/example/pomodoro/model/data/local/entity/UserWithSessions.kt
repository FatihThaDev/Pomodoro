package com.example.pomodoro.model.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithSessions(
    @Embedded val user: UserEntity,
    @Relation(parentColumn = "userId", entityColumn = "userId")
    val sessions: List<SessionEntity>
)
