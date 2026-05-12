package com.example.pomodoro.model.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pomodoro.model.data.local.entity.SessionProjectCrossRef

@Dao
interface SessionProjectDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(crossRef: SessionProjectCrossRef)

    @Delete
    suspend fun delete(crossRef: SessionProjectCrossRef)

    @Query("SELECT * FROM session_project WHERE sessionId = :sessionId")
    suspend fun getProjectsForSession(sessionId: Long): List<SessionProjectCrossRef>

    @Query("SELECT * FROM session_project WHERE projectId = :projectId")
    suspend fun getSessionsForProject(projectId: Long): List<SessionProjectCrossRef>
}
