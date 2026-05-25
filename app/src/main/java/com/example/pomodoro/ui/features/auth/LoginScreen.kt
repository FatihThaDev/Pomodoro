package com.example.pomodoro.ui.features.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.pomodoro.ui.navigation.Screen
import com.example.pomodoro.ui.theme.PomodoroTheme
import com.example.pomodoro.ui.components.HeadingText
import com.example.pomodoro.ui.features.auth.util.Validation

@Composable
internal fun LoginScreen(
    username: String,
    password: String,
    usernameError: Boolean,
    passwordError: Boolean,
    usernameChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
    isSubmitEnabled: Boolean,
    onLoginClick: () -> Unit
) {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = Color(0x50AA5077))
            .fillMaxSize()
            .padding(vertical = 35.dp, horizontal = 20.dp)
    ) {
        item {
            HeadingText("Log In")
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    value = username,
                    onValueChange = {
                        usernameChange(it)
                    },
                    label = { Text("Username") },
                    placeholder = { Text("JohnD67") },
                    isError = usernameError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                if (usernameError) {
                    Text("Username must be at least 3 characters", color = Color.Red)
                }

                TextField(
                    value = password,
                    onValueChange = {
                        passwordChange(it)
                    },
                    label = { Text("Password") },
                    placeholder = { Text("Supersecurepass123") },
                    isError = passwordError,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                )
                if (passwordError) {
                    Text("Password must be at least 6 characters", color = Color.Red)
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Button(
                    onClick = onLoginClick,
                    enabled = isSubmitEnabled,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Log In")
                }
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Forgot Password?")
                }
            }
        }
    }
}

@Composable
fun Login(navController: NavController) {
    val viewModel: LoginViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var errorMessage by remember { mutableStateOf<String?>(null) }

    var username by rememberSaveable { mutableStateOf("") }
    var usernameError by rememberSaveable { mutableStateOf(false) }

    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf(false) }

    val isSubmitEnabled by remember { derivedStateOf { username.isNotEmpty() && password.isNotEmpty() &&
            !usernameError && !passwordError } }

    LaunchedEffect(uiState) {
        when (uiState) {
            is LoginUiState.Success -> {
                navController.navigate(Screen.Dashboard.createRoute((uiState as LoginUiState.Success).username)) {
                    popUpTo(Screen.Login.route) { inclusive = false }
                }
                viewModel.resetUiState()
            }
            is LoginUiState.Error -> {
                errorMessage = (uiState as LoginUiState.Error).message
            }
            else -> {}
        }
    }

    LoginScreen(
        username = username,
        password = password,
        usernameError = usernameError,
        passwordError = passwordError,
        isSubmitEnabled = isSubmitEnabled && uiState !is LoginUiState.Loading,
        usernameChange = {
            username = it
            usernameError = !Validation.isValidUsername(it)
            errorMessage = null
        },
        passwordChange = {
            password = it
            passwordError = !Validation.isValidPassword(it)
            errorMessage = null
        },
        onLoginClick = {
            errorMessage = null
            viewModel.onLoginClick(username, password)
        }
    )

    if (errorMessage != null) {
        AlertDialog(
            onDismissRequest = { errorMessage = null },
            title = { Text("Login Failed") },
            text = { Text(errorMessage!!) },
            confirmButton = {
                TextButton(onClick = { errorMessage = null }) {
                    Text("OK")
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewLogin() {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var usernameError by rememberSaveable { mutableStateOf(false) }
    var passwordError by rememberSaveable { mutableStateOf(false) }

    PomodoroTheme {
        LoginScreen(
            username = username,
            password = password,
            usernameError = usernameError,
            passwordError = passwordError,
            usernameChange = {
                username = it
                usernameError = !Validation.isValidUsername(it)
            },
            passwordChange = {
                password = it
                passwordError = !Validation.isValidPassword(it)
            },
            isSubmitEnabled = username.isNotEmpty() && password.isNotEmpty() && !usernameError && !passwordError,
            onLoginClick = {}
        )
    }
}
