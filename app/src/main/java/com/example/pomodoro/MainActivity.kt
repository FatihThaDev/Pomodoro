package com.example.pomodoro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pomodoro.presentation.navigation.BottomNavigationBar
import com.example.pomodoro.presentation.navigation.NavGraph
import com.example.pomodoro.presentation.navigation.Screen
import com.example.pomodoro.presentation.theme.PomodoroTheme
import com.example.pomodoro.presentation.ui.screens.about.AboutScreen
import com.example.pomodoro.presentation.ui.screens.dashboard.DashboardScreen
import com.example.pomodoro.presentation.ui.screens.dashboard.util.SessionData
import com.example.pomodoro.presentation.ui.screens.donate.Donate
import com.example.pomodoro.presentation.ui.screens.login.LoginScreen
import com.example.pomodoro.presentation.ui.util.Validation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PomodoroTheme {
                PomodoroApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PomodoroApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        Screen.Dashboard.route,
        Screen.About.route,
        Screen.Login.route,
        Screen.Register.route,
        Screen.Donate.route
    )

    val topBarTitle = when (currentRoute) {
        Screen.Dashboard.route -> "Pomodoro Timer"
        Screen.About.route -> "About"
        Screen.Login.route -> "Log In"
        Screen.Register.route -> "Register"
        Screen.Donate.route -> "Donate"
        else -> ""
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (showBottomBar) {
                TopAppBar(
                    title = { Text(topBarTitle) }
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavGraph(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewPomodoroApp() {
    var selectedRoute by remember { mutableStateOf(Screen.Dashboard.route) }
    var dashboardUsername by rememberSaveable { mutableStateOf("Guest") }
    var selectedProject by remember { mutableStateOf<Pair<String, String>?>(null) }

    PomodoroTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                if (selectedRoute != "projectDetails") {
                    TopAppBar(
                        title = { Text(when (selectedRoute) {
                            Screen.Dashboard.route -> "Pomodoro Timer"
                            Screen.About.route -> "About"
                            Screen.Login.route -> "Log In"
                            Screen.Register.route -> "Register"
                            Screen.Donate.route -> "Donate"
                            else -> "Pomodoro"
                        }) }
                    )
                }
            },
            bottomBar = {
                BottomNavigationBarPreview(
                    selectedRoute = selectedRoute,
                    onRouteSelected = { selectedRoute = it }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                when (selectedRoute) {
                Screen.Dashboard.route -> DashboardScreen(
                    username = dashboardUsername,
                    minutes = 25,
                    seconds = 0,
                    isRunning = false,
                    isTimerFinished = false,
                    sessionData = SessionData(),
                    startTimer = {},
                    pauseTimer = {},
                    resetTimer = {}
                )
                Screen.About.route -> {
                    var searchQuery by remember { mutableStateOf("") }
                    AboutScreen(
                        searchQuery = searchQuery,
                        valueChange = { searchQuery = it },
                        onProjectClick = { label, desc ->
                            selectedProject = label to desc
                            selectedRoute = "projectDetails"
                        }
                    )
                }
                "projectDetails" -> {
                    com.example.pomodoro.presentation.ui.screens.about.ProjectDetailsScreen(
                        label = selectedProject?.first ?: "",
                        description = selectedProject?.second ?: "",
                        onBackClick = { selectedRoute = Screen.About.route }
                    )
                }
                Screen.Login.route -> {
                    var username by rememberSaveable { mutableStateOf("") }
                    var password by rememberSaveable { mutableStateOf("") }

                    var usernameError by rememberSaveable { mutableStateOf(false) }
                    var passwordError by rememberSaveable { mutableStateOf(false) }

                    val isSubmitEnabled by remember { derivedStateOf {
                        username.isNotEmpty() && password.isNotEmpty() && !usernameError && !passwordError
                    } }

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
                        isSubmitEnabled = isSubmitEnabled,
                        onLoginClick = {
                            dashboardUsername = username
                            selectedRoute = Screen.Dashboard.route
                        }
                    )
                }
                Screen.Register.route -> {
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
                            username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() &&
                            !firstNameError && !lastNameError && !usernameError && !emailError && !passwordError

                    com.example.pomodoro.presentation.ui.screens.register.RegisterScreen(
                        firstName = firstName,
                        firstNameChange = {
                            firstName = it
                            firstNameError = !Validation.isValidName(it)
                        },
                        firstNameError = firstNameError,
                        lastName = lastName,
                        lastNameChange = {
                            lastName = it
                            lastNameError = !Validation.isValidName(it)
                        },
                        lastNameError = lastNameError,
                        username = username,
                        usernameChange = {
                            username = it
                            usernameError = !Validation.isValidUsername(it)
                        },
                        usernameError = usernameError,
                        email = email,
                        emailChange = {
                            email = it
                            emailError = !Validation.isValidEmail(it)
                        },
                        emailError = emailError,
                        password = password,
                        passwordChange = {
                            password = it
                            passwordError = !Validation.isValidPassword(it)
                        },
                        passwordError = passwordError,
                        isSubmitEnabled = isSubmitEnabled,
                        onRegisterClick = {
                            dashboardUsername = username
                            selectedRoute = Screen.Dashboard.route
                        }
                    )
                }
                Screen.Donate.route -> Donate()
                else -> {}
                }
            }
        }
    }
}

@Composable
private fun BottomNavigationBarPreview(
    selectedRoute: String,
    onRouteSelected: (String) -> Unit
) {
    val items =  listOf(
        Screen.Dashboard.route to "Dashboard",
        Screen.About.route to "About",
        Screen.Login.route to "Log In",
        Screen.Register.route to "Register",
        Screen.Donate.route to "Donate"
    )

    NavigationBar {
        items.forEach { (route, label) ->
            NavigationBarItem(
                icon = { Text(route.take(1)) },
                label = { Text(label) },
                selected = selectedRoute == route,
                onClick = { onRouteSelected(route) }
            )
        }
    }
}
