package com.example.pomodoro.ui.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodoro.data.session.UserSession
import com.example.pomodoro.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RegisterUiState {
    object Init : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success(val username: String) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userSession: UserSession
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Init)
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onRegisterClick(username: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading
            try {
                val user = userRepository.register(username, email, password)
                userSession.currentUserId = user.userId
                _uiState.value = RegisterUiState.Success(user.username)
            } catch (e: Exception) {
                _uiState.value = RegisterUiState.Error(e.message ?: "Registration failed")
            }
        }
    }

    fun resetUiState() {
        _uiState.value = RegisterUiState.Init
    }
}
