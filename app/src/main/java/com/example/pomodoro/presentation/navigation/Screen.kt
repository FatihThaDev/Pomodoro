package com.example.pomodoro.presentation.navigation

sealed class Screen(val route: String) {
    data object Dashboard : Screen("dashboard?username={username}") {
        fun createRoute(username: String) = "dashboard?username=$username"
    }
    data object About : Screen("about")
    data object Donate : Screen("donate")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object ProjectDetails : Screen("project/{label}/{description}") {
        fun createRoute(label: String, description: String) = "project/$label/$description"
    }
}
