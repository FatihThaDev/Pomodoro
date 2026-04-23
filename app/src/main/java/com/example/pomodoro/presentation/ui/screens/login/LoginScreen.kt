package com.example.pomodoro.presentation.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import com.example.pomodoro.presentation.theme.PomodoroTheme
import com.example.pomodoro.presentation.ui.components.HeadingText
import com.example.pomodoro.presentation.ui.util.Validation

@Composable
private fun LoginScreen(
    username: String,
    password: String,
    usernameError: Boolean,
    passwordError: Boolean,
    usernameChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
    isSubmitEnabled: Boolean,
    onLoginSuccess: (String) -> Unit
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
                    onClick = { onLoginSuccess(username) },
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
fun Login(onLoginSuccess: (String) -> Unit) {
    var username by rememberSaveable { mutableStateOf("") }
    var usernameError by rememberSaveable {mutableStateOf(false)}

    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable {mutableStateOf(false)}

    val isSubmitEnabled by remember { derivedStateOf { username.isNotEmpty() && password.isNotEmpty() &&
            !usernameError && !passwordError } }


    LoginScreen(
        username = username,
        password = password,
        usernameError = usernameError,
        passwordError = passwordError,
        isSubmitEnabled = isSubmitEnabled,
        usernameChange = {
            username = it
            usernameError = !Validation.isValidUsername(it)
        },
        passwordChange = {
            password = it
            passwordError = !Validation.isValidPassword(it)
        },
        onLoginSuccess = onLoginSuccess
        )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewLogin() {
    PomodoroTheme {
        Login { _ -> }
    }
}
