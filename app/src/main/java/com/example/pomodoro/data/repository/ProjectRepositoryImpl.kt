package com.example.pomodoro.data.repository

import com.example.pomodoro.data.local.dao.ProjectDao
import com.example.pomodoro.data.local.entity.ProjectEntity
import com.example.pomodoro.data.mapper.toDomain
import com.example.pomodoro.data.mapper.toEntity
import com.example.pomodoro.domain.model.Project
import com.example.pomodoro.domain.repository.ProjectRepository
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val projectDao: ProjectDao
) : ProjectRepository {

    override suspend fun createProject(name: String, description: String): Long {
        return projectDao.insert(ProjectEntity(name = name, description = description))
    }

    override suspend fun getAllProjects(): List<Project> {
        return projectDao.getAllProjects().map { it.toDomain() }
    }

    override suspend fun updateProject(project: Project) {
        projectDao.update(project.toEntity())
    }

    override suspend fun deleteProject(project: Project) {
        projectDao.delete(project.toEntity())
    }
}
