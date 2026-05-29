package com.example.pomodoro.domain.repository

import com.example.pomodoro.domain.model.Project

interface ProjectRepository {
    suspend fun createProject(name: String, description: String): Long
    suspend fun getAllProjects(): List<Project>
    suspend fun updateProject(project: Project)
    suspend fun deleteProject(project: Project)
}
