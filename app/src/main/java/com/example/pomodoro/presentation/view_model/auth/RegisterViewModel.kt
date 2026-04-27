package com.example.pomodoro.presentation.view_model.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodoro.model.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RegisterUiState {
    object Init : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success(val isRegistered: Boolean) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Init)
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onRegisterClick(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading

            if (authRepository.login(email, password)) {
                _uiState.value = RegisterUiState.Success(isRegistered = true)
            } else {
                _uiState.value = RegisterUiState.Error("Registration failed")
            }
        }
    }

    fun resetUiState() {
        _uiState.value = RegisterUiState.Init
    }
}