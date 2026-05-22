package com.example.pomodoro.data.repository

import com.example.pomodoro.data.local.dao.SessionDao
import com.example.pomodoro.data.local.entity.SessionEntity
import com.example.pomodoro.data.mapper.toDomain
import com.example.pomodoro.data.mapper.toEntity
import com.example.pomodoro.domain.model.Session
import com.example.pomodoro.domain.repository.SessionRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionDao: SessionDao
) : SessionRepository {

    override suspend fun createSession(userId: Long, focusMinutes: Int): Long {
        return sessionDao.insert(SessionEntity(userId = userId, focusMinutes = focusMinutes))
    }

    override suspend fun getSessionsByUserId(userId: Long): List<Session> {
        return sessionDao.getSessionsByUserId(userId).map { it.toDomain() }
    }

    override suspend fun getAllSessions(): List<Session> {
        return sessionDao.getAllSessions().map { it.toDomain() }
    }

    override suspend fun deleteSession(session: Session) {
        sessionDao.delete(session.toEntity())
    }
}
