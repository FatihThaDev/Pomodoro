package com.example.pomodoro.presentation.ui.screens.about.util

fun filterProjects(projects: List<Project>, query: String): List<Project> {
    if (query.isEmpty()) return projects
    return projects.filter { project ->
        project.label.contains(query, ignoreCase = true) ||
        project.value.contains(query, ignoreCase = true)
    }
}
