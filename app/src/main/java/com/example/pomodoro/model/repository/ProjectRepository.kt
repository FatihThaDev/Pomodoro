package com.example.pomodoro.model.repository

import com.example.pomodoro.model.data.local.entity.ProjectEntity

interface ProjectRepository {
    suspend fun createProject(name: String, description: String): Long
    suspend fun getAllProjects(): List<ProjectEntity>
    suspend fun updateProject(project: ProjectEntity)
    suspend fun deleteProject(project: ProjectEntity)
}
