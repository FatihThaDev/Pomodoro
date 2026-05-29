package com.example.pomodoro.data.mapper

import com.example.pomodoro.data.local.entity.SessionEntity
import com.example.pomodoro.domain.model.Session

fun SessionEntity.toDomain(): Session = Session(
    sessionId = sessionId,
    userId = userId,
    focusMinutes = focusMinutes,
    completedAt = completedAt
)

fun Session.toEntity(): SessionEntity = SessionEntity(
    sessionId = sessionId,
    userId = userId,
    focusMinutes = focusMinutes,
    completedAt = completedAt
)
