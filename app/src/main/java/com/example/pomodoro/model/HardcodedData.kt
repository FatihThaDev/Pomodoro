package com.example.pomodoro.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Call
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.PlayArrow
import com.example.pomodoro.presentation.ui.screens.about.util.Project


val projectsList = listOf(
    Project(icon = Icons.TwoTone.AccountCircle, label = "FatihNvim", value = "Neovim config for power users"),
    Project(icon = Icons.TwoTone.Favorite, label = "archlinux-install", value = "Arch Linux install script"),
    Project(icon = Icons.TwoTone.Call, label = "foodify-delivery-app", value = "Next.js + Tailwind food delivery app"),
    Project(icon = Icons.TwoTone.PlayArrow, label = "Cave-Slayers", value = "2D action game in Python")
)