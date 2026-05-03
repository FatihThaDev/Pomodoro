package com.example.pomodoro.presentation.navigation

import java.net.URLEncoder
import java.net.URLDecoder

sealed class Screen(val route: String) {
    data object Dashboard : Screen("dashboard?username={username}") {
        fun createRoute(username: String) = "dashboard?username=$username"
    }
    data object About : Screen("about")
    data object Donate : Screen("donate")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object ProjectDetails : Screen("project") {
        fun createRoute(label: String, description: String) = "project?label=${URLEncoder.encode(label, "UTF-8")}&desc=${URLEncoder.encode(description, "UTF-8")}"
    }
}
