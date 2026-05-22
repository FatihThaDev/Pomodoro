package com.example.pomodoro.domain.repository

import com.example.pomodoro.domain.model.Session

interface SessionRepository {
    suspend fun createSession(userId: Long, focusMinutes: Int): Long
    suspend fun getSessionsByUserId(userId: Long): List<Session>
    suspend fun getAllSessions(): List<Session>
    suspend fun deleteSession(session: Session)
}
