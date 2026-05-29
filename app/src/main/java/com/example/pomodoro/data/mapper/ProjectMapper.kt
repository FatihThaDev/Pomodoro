package com.example.pomodoro.data.mapper

import com.example.pomodoro.data.local.entity.ProjectEntity
import com.example.pomodoro.domain.model.Project

fun ProjectEntity.toDomain(): Project = Project(
    projectId = projectId,
    name = name,
    description = description
)

fun Project.toEntity(): ProjectEntity = ProjectEntity(
    projectId = projectId,
    name = name,
    description = description
)
