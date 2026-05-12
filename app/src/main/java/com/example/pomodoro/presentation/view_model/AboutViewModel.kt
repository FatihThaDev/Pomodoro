package com.example.pomodoro.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodoro.model.data.local.entity.ProjectEntity
import com.example.pomodoro.model.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AboutUiState(
    val searchQuery: String = "",
    val appVersion: String = "1.0.0",
    val projects: List<ProjectEntity> = emptyList()
)

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AboutUiState())
    val uiState: StateFlow<AboutUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch { loadProjects() }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    private suspend fun loadProjects() {
        val projects = projectRepository.getAllProjects()
        _uiState.value = _uiState.value.copy(projects = projects)
    }
}
