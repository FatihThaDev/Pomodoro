package com.example.pomodoro.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(
    val route: String,
    val icon: @Composable () -> Unit,
    val label: String
)

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(
            route = Screen.Dashboard.route,
            icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard") },
            label = "Dashboard"
        ),
        BottomNavItem(
            route = Screen.About.route,
            icon = { Icon(Icons.Default.Info, contentDescription = "About") },
            label = "About"
        ),
        BottomNavItem(
            route = Screen.Login.route,
            icon = { Icon(Icons.Default.Lock, contentDescription = "Login") },
            label = "Log In"
        ),
        BottomNavItem(
            route = Screen.Donate.route,
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Donate") },
            label = "Donate"
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(Screen.Dashboard.route) { saveState = true }
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}