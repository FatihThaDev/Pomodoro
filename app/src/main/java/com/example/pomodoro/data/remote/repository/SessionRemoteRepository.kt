package com.example.pomodoro.data.remote.repository

import com.example.pomodoro.data.remote.api.CreateSessionRequest
import com.example.pomodoro.data.remote.api.PomodoroApi
import com.example.pomodoro.data.remote.api.UpdateSessionRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionRemoteRepository @Inject constructor(
    private val api: PomodoroApi
) {
    suspend fun createSession(userId: Long, focusMinutes: Int) {
        api.createSession(CreateSessionRequest(userId, focusMinutes))
    }

    suspend fun updateSession(id: Long, focusMinutes: Int) {
        api.updateSession(id, UpdateSessionRequest(focusMinutes))
    }

    suspend fun deleteSession(id: Long) {
        api.deleteSession(id)
    }
}