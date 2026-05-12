package com.example.pomodoro.model.repository.impl

import com.example.pomodoro.model.data.local.dao.ProjectDao
import com.example.pomodoro.model.data.local.entity.ProjectEntity
import com.example.pomodoro.model.repository.ProjectRepository
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val projectDao: ProjectDao
) : ProjectRepository {

    override suspend fun createProject(name: String, description: String): Long {
        return projectDao.insert(ProjectEntity(name = name, description = description))
    }

    override suspend fun getAllProjects(): List<ProjectEntity> {
        return projectDao.getAllProjects()
    }

    override suspend fun updateProject(project: ProjectEntity) {
        projectDao.update(project)
    }

    override suspend fun deleteProject(project: ProjectEntity) {
        projectDao.delete(project)
    }
}
