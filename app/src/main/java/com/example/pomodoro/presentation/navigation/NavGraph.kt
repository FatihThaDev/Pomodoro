package com.example.pomodoro.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pomodoro.presentation.ui.screens.about.About
import com.example.pomodoro.presentation.ui.screens.about.ProjectDetailsScreen
import com.example.pomodoro.presentation.ui.screens.dashboard.Dashboard
import com.example.pomodoro.presentation.ui.screens.donate.Donate
import com.example.pomodoro.presentation.ui.screens.login.Login
import com.example.pomodoro.presentation.ui.screens.register.Register

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
        modifier = modifier
    ) {
        composable(
            route = Screen.Dashboard.route,
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                    defaultValue = "Guest"
                }
            )
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Guest"
            Dashboard(username = username)
        }
        composable(Screen.About.route) {
            About(
                onProjectClick = { label, description ->
                    navController.navigate(Screen.ProjectDetails.createRoute(label, description))
                }
            )
        }
        composable(Screen.Donate.route) {
            Donate()
        }
        composable(Screen.Login.route) {
            Login(
                onLoginSuccess = { username ->
                    navController.navigate(Screen.Dashboard.createRoute(username)) {
                        popUpTo(Screen.Login.route) { inclusive = false }
                    }
                }
            )
        }
        composable(Screen.Register.route) {
            Register(
                onRegisterSuccess = { username ->
                    navController.navigate(Screen.Dashboard.createRoute(username)) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = Screen.ProjectDetails.route,
            arguments = listOf(
                navArgument("label") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val label = backStackEntry.arguments?.getString("label") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            ProjectDetailsScreen(
                label = label,
                description = description,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
