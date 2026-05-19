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
import java.net.URLDecoder

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
            Login(navController = navController)
        }
        composable(Screen.Register.route) {
            Register(navController = navController)
        }
        composable(
            route = Screen.ProjectDetails.route,
            arguments = listOf(
                navArgument("label") { type = NavType.StringType; defaultValue = "" },
                navArgument("desc") { type = NavType.StringType; defaultValue = "" }
            )
        ) { backStackEntry ->
            val label = URLDecoder.decode(backStackEntry.arguments?.getString("label") ?: "", "UTF-8")
            val description = URLDecoder.decode(backStackEntry.arguments?.getString("desc") ?: "", "UTF-8")
            ProjectDetailsScreen(
                label = label,
                description = description,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
