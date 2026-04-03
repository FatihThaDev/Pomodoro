package com.example.pomodoro.presentation.ui.screens.register

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
fun RegisterScreen(
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    firstNameError: Boolean,
    lastName: String,
    onLastNameChange: (String) -> Unit,
    lastNameError: Boolean,
    username: String,
    onUsernameChange: (String) -> Unit,
    usernameError: Boolean,
    email: String,
    onEmailChange: (String) -> Unit,
    emailError: Boolean,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordError: Boolean,
    isSubmitEnabled: Boolean
)
{
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
                    onFirstNameChange(it)
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
                    onLastNameChange(it)
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
                    onUsernameChange(it)
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
                    onEmailChange(it)
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
                    onPasswordChange(it)
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
                enabled = isSubmitEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create Account")
            }
        }
    }
}

@Composable
private fun RegisterState() {
    var firstName by rememberSaveable { mutableStateOf("") }
    var firstNameError by rememberSaveable { mutableStateOf(false) }

    var lastName by rememberSaveable { mutableStateOf("") }
    var lastNameError by rememberSaveable { mutableStateOf(false) }

    var username by rememberSaveable { mutableStateOf("") }
    var usernameError by rememberSaveable { mutableStateOf(false) }

    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf(false) }

    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf(false) }

    val isSubmitEnabled = firstName.isNotEmpty() && lastName.isNotEmpty() &&
            username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
            && !firstNameError && !lastNameError && !usernameError && !emailError && !passwordError

    RegisterScreen(
        firstName = firstName,
        onFirstNameChange = {
            firstName = it
            firstNameError = !Validation.isValidName(it)
        },
        firstNameError = firstNameError,
        lastName = lastName,
        onLastNameChange = {
            lastName = it
            lastNameError = !Validation.isValidName(it)
        },
        lastNameError = lastNameError,
        username = username,
        onUsernameChange = {
            username = it
            usernameError = !Validation.isValidUsername(it)
        },
        usernameError = usernameError,
        email = email,
        onEmailChange = {
            email = it
            emailError = !Validation.isValidEmail(it)
        },
        emailError = emailError,
        password = password,
        onPasswordChange = {
            password = it
            passwordError = !Validation.isValidPassword(it)
        },
        passwordError = passwordError,
        isSubmitEnabled = isSubmitEnabled
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegister() {
    PomodoroTheme {
        RegisterState()
    }
}
