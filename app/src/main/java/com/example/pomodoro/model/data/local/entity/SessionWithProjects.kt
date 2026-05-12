package com.example.pomodoro.model.data.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SessionWithProjects(
    @Embedded val session: SessionEntity,
    @Relation(
        parentColumn = "sessionId",
        entityColumn = "projectId",
        associateBy = Junction(
            value = SessionProjectCrossRef::class,
            parentColumn = "sessionId",
            entityColumn = "projectId"
        )
    )
    val projects: List<ProjectEntity>
)
