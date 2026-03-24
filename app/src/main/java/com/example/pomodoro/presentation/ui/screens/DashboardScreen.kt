package com.example.pomodoro.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pomodoro.presentation.ui.components.BodyText
import com.example.pomodoro.presentation.ui.components.Card
import com.example.pomodoro.presentation.ui.components.HeadingText

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(19.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadingText("FatihTheDev's Pomodoro timer")
        BodyText("Welcome to FatihTheDev's " +
        "Pomodoro timer. Increased productivity has never been easier.")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDashboard(){
    DashboardScreen()
}