package com.example.pomodoro.presentation.ui.screens

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun Register() {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var firstNameError by remember { mutableStateOf(false) }
    var lastNameError by remember { mutableStateOf(false) }
    var usernameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    val submitButtonAvailable = (firstName.isNotEmpty() && lastName.isNotEmpty()
            && username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty())
            && !firstNameError && !lastNameError && !usernameError && !emailError
            && !passwordError

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = Color(0x50AA5077))
            .fillMaxSize()
            .padding(vertical = 35.dp, horizontal = 20.dp)
    ) {
        HeadingText("Create Account")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = firstName,
                onValueChange = {
                    firstName = it
                    firstNameError = it.length < 2 && it.isNotEmpty()
                },
                label = { Text("First Name") },
                placeholder = { Text("John") },
                isError = firstNameError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            if (firstNameError) {
                Text("First name must be at least 2 characters", color = Color.Red)
            }

            TextField(
                value = lastName,
                onValueChange = {
                    lastName = it
                    lastNameError = it.length < 2 && it.isNotEmpty()
                },
                label = { Text("Last Name") },
                placeholder = { Text("Doe") },
                isError = lastNameError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            if (lastNameError) {
                Text("Last name must be at least 2 characters", color = Color.Red)
            }

            TextField(
                value = username,
                onValueChange = {
                    username = it
                    usernameError = it.length < 3 && it.isNotEmpty()
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
                value = email,
                onValueChange = {
                    email = it
                    emailError = !Patterns.EMAIL_ADDRESS.matcher(it).matches() && it.isNotEmpty()
                },
                label = { Text("Email") },
                placeholder = { Text("Your account email") },
                isError = emailError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            if (emailError) {
                Text("Invalid email address", color = Color.Red)
            }

            TextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = it.length < 6 && it.isNotEmpty()
                },
                label = { Text("Password") },
                placeholder = { Text("Your account password") },
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {},
                enabled = submitButtonAvailable,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create Account")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegister() {
    PomodoroTheme {
        Register()
    }
}
