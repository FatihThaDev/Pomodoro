package com.example.pomodoro.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pomodoro.presentation.ui.components.BodyText
import com.example.pomodoro.presentation.ui.components.Card
import com.example.pomodoro.presentation.ui.components.HeadingText

@Composable
fun Dashboard() {
    Column(
        modifier = Modifier.
        fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadingText("FatihTheDev's Pomodoro timer")
        BodyText("Welcome to FatihTheDev's " +
        "Pomodoro timer. Increased productivity has never been easier.")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboard(){
    Dashboard()
}