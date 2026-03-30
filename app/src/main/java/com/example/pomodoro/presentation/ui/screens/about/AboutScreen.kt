package com.example.pomodoro.presentation.ui.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
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
fun About() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = Color(0x50AA5077))
            .fillMaxSize()
            .padding(vertical = 35.dp, horizontal = 20.dp)
    ) {
        HeadingText("About Pomodoro")
        BodyText("Pomodoro is a time management method that breaks " +
                "work into focused intervals to boost productivity " +
                "and reduce burnout")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            InfoRow(icon = Icons.Default.Person, label = "Developer", value = "FatihTheDev")
            InfoRow(icon = Icons.Default.Info, label = "Version", value = "1.0.0")
            InfoRow(icon = Icons.Default.Build, label = "License", value = "MIT")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAbout() {
    PomodoroTheme {
        About()
    }
}
