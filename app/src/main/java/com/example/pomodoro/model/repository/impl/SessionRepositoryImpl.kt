package com.example.pomodoro.model.repository.impl

import com.example.pomodoro.model.data.local.dao.SessionDao
import com.example.pomodoro.model.data.local.entity.SessionEntity
import com.example.pomodoro.model.repository.SessionRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionDao: SessionDao
) : SessionRepository {

    override suspend fun createSession(userId: Long, focusMinutes: Int): Long {
        return sessionDao.insert(SessionEntity(userId = userId, focusMinutes = focusMinutes))
    }

    override suspend fun getSessionsByUserId(userId: Long): List<SessionEntity> {
        return sessionDao.getSessionsByUserId(userId)
    }

    override suspend fun getAllSessions(): List<SessionEntity> {
        return sessionDao.getAllSessions()
    }

    override suspend fun deleteSession(session: SessionEntity) {
        sessionDao.delete(session)
    }
}
