package com.example.pomodoro.presentation.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodoro.presentation.theme.PomodoroTheme
import com.example.pomodoro.presentation.ui.components.HeadingText
import com.example.pomodoro.presentation.ui.components.InfoRow
import kotlinx.coroutines.delay

@Composable
fun DashboardScreen() {

    var minutes by rememberSaveable { mutableIntStateOf(25) }
    var seconds by rememberSaveable { mutableIntStateOf(0) }
    var isRunning by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = Color(0x50AA5077))
            .fillMaxSize()
            .padding(vertical = 35.dp, horizontal = 20.dp)
    ) {
        HeadingText("Pomodoro Timer")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${formatTime(minutes)}:${formatTime(seconds)}",
                fontSize = 70.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                Button(onClick = {}) {
                    Text("Start")
                }
                OutlinedButton(onClick = { isRunning = false }) {
                    Text("Pause")
                }
                OutlinedButton(onClick = {
                    minutes = 25
                    seconds = 0
                    isRunning = false
                }) {
                    Text("Reset")
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Statistics",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            InfoRow(label = "Sessions Completed", value = "12")
            InfoRow(label = "Total Focus Time", value = "5h 00m")
            InfoRow(label = "Today's Sessions", value = "4")
            InfoRow(label = "Current Streak", value = "3 days")
        }
    }
}

private fun formatTime(time: Int): String {
    return if (time < 10) {
        "0${time}"
    } else {
        time.toString()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDashboard() {
    PomodoroTheme {
        DashboardScreen()
    }
}
