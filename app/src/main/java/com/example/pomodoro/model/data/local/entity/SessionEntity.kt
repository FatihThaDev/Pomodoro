package com.example.pomodoro.model.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sessions",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["userId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("userId")]
)
data class SessionEntity(
    @PrimaryKey(autoGenerate = true) val sessionId: Long = 0,
    val userId: Long,
    val focusMinutes: Int,
    val completedAt: Long = System.currentTimeMillis()
)
