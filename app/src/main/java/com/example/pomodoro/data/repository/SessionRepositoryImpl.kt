package com.example.pomodoro.data.repository

import com.example.pomodoro.data.local.dao.SessionDao
import com.example.pomodoro.data.local.entity.SessionEntity
import com.example.pomodoro.data.mapper.toDomain
import com.example.pomodoro.data.mapper.toEntity
import com.example.pomodoro.data.remote.repository.SessionRemoteRepository
import com.example.pomodoro.domain.model.Session
import com.example.pomodoro.domain.repository.SessionRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionDao: SessionDao,
    private val remoteRepository: SessionRemoteRepository
) : SessionRepository {

    override suspend fun createSession(userId: Long, focusMinutes: Int): Long {
        val id = sessionDao.insert(SessionEntity(userId = userId, focusMinutes = focusMinutes))
        try { remoteRepository.createSession(userId, focusMinutes) } catch (_: Exception) { }
        return id
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
