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

sealed class LoginUiState {
    object Init : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val username: String) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userSession: UserSession
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Init)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onLoginClick(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            val user = userRepository.login(username, password)
            if (user != null) {
                userSession.currentUserId = user.userId
                _uiState.value = LoginUiState.Success(user.username)
            } else {
                _uiState.value = LoginUiState.Error("Invalid credentials")
            }
        }
    }

    fun resetUiState() {
        _uiState.value = LoginUiState.Init
    }
}
