package com.example.pomodoro.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pomodoro.ui.features.about.About
import com.example.pomodoro.ui.features.about.ProjectDetailsScreen
import com.example.pomodoro.ui.features.dashboard.Dashboard
import com.example.pomodoro.ui.features.donate.Donate
import com.example.pomodoro.ui.features.auth.Login
import com.example.pomodoro.ui.features.auth.Register
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
            val label = backStackEntry.arguments?.getString("label") ?: ""
            val description = backStackEntry.arguments?.getString("desc") ?: ""
            ProjectDetailsScreen(
                label = label,
                description = description,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
