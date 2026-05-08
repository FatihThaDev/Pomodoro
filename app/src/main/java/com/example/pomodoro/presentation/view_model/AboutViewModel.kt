package com.example.pomodoro.presentation.view_model

import androidx.lifecycle.ViewModel
import com.example.pomodoro.model.AboutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class AboutUiState(
    val searchQuery: String = "",
    val appVersion: String = ""
)

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val aboutRepository: AboutRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AboutUiState(appVersion = aboutRepository.getAppVersion()))
    val uiState: StateFlow<AboutUiState> = _uiState.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }
}
