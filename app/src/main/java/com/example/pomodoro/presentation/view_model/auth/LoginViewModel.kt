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

sealed class LoginUiState {
    object Init : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val username: String) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Init)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onLoginClick(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading

            if (authRepository.login(username, password)) {
                _uiState.value = LoginUiState.Success(username)
            }
            else {
                _uiState.value = LoginUiState.Error("Invalid credentials")
            }
        }
    }

    fun resetUiState() {
        _uiState.value = LoginUiState.Init
    }
}
