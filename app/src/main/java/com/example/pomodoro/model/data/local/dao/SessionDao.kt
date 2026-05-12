package com.example.pomodoro.model.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.pomodoro.model.data.local.entity.SessionEntity
import com.example.pomodoro.model.data.local.entity.SessionWithProjects

@Dao
interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(session: SessionEntity): Long

    @Update
    suspend fun update(session: SessionEntity)

    @Delete
    suspend fun delete(session: SessionEntity)

    @Query("SELECT * FROM sessions WHERE sessionId = :sessionId LIMIT 1")
    suspend fun getSessionById(sessionId: Long): SessionEntity?

    @Query("SELECT * FROM sessions WHERE userId = :userId ORDER BY completedAt DESC")
    suspend fun getSessionsByUserId(userId: Long): List<SessionEntity>

    @Query("SELECT * FROM sessions ORDER BY completedAt DESC")
    suspend fun getAllSessions(): List<SessionEntity>

    @Transaction
    @Query("SELECT * FROM sessions WHERE sessionId = :sessionId")
    suspend fun getSessionWithProjects(sessionId: Long): SessionWithProjects?
}
