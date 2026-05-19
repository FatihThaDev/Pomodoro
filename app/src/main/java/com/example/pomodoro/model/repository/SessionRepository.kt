package com.example.pomodoro.model.repository

import com.example.pomodoro.model.data.local.entity.SessionEntity

interface SessionRepository {
    suspend fun createSession(userId: Long, focusMinutes: Int): Long
    suspend fun getSessionsByUserId(userId: Long): List<SessionEntity>
    suspend fun getAllSessions(): List<SessionEntity>
    suspend fun deleteSession(session: SessionEntity)
}
