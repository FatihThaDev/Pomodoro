package com.example.pomodoro.presentation.ui.screens.donate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pomodoro.presentation.theme.PomodoroTheme
import com.example.pomodoro.presentation.ui.components.BodyText
import com.example.pomodoro.presentation.ui.components.HeadingText
import com.example.pomodoro.presentation.ui.components.InfoRow

@Composable
fun Donate() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = Color(0x50AA5077))
            .fillMaxSize()
            .padding(vertical = 35.dp, horizontal = 20.dp)
    ) {
        HeadingText("Support My Work")
        BodyText("Your donations help me keep creating free apps!")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            InfoRow(icon = Icons.Default.Email, label = "PayPal", value = "paypal@example.com")
            InfoRow(icon = Icons.Default.Face, label = "Bitcoin", value = "1A2B3C4D5E6F")
            InfoRow(icon = Icons.Default.Favorite, label = "Patreon", value = "patreon.com/FatihTheDev")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDonate() {
    PomodoroTheme {
        Donate()
    }
}
