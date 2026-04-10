package com.example.pomodoro.presentation.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun DashboardScreen(
    minutes: Int,
    seconds: Int,
    isRunning: Boolean,
    isTimerFinished: Boolean,
    pauseTimer: () -> Unit,
    resetTimer: () -> Unit,
) {

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

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                item {
                    Button(onClick = {}) {
                        Text("Start")
                    }
                }
                item {
                    OutlinedButton(onClick = { pauseTimer() }) {
                        Text("Pause")
                    }
                }
                item {
                    OutlinedButton(onClick = { resetTimer() }) {
                        Text("Reset")
                    }
                }
            }
        }

        if(isTimerFinished) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                item {
                    Text("Timer has finished!", color = Color.Red)
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = if(!isTimerFinished) 48.dp else 30.dp),
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

@Composable
fun Dashboard() {
    var minutes by rememberSaveable { mutableIntStateOf(25) }
    var seconds by rememberSaveable { mutableIntStateOf(0) }

    var isRunning by rememberSaveable { mutableStateOf(false) }
    val isTimerFinished by remember { derivedStateOf { minutes == 0 && seconds == 0 } }

    DashboardScreen(
        minutes = minutes,
        seconds = seconds,
        isRunning = isRunning,
        isTimerFinished = isTimerFinished,
        pauseTimer = { isRunning = false },
        resetTimer = {
            minutes = 0
            seconds = 0
            isRunning = false
        }
    )
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
private fun PreviewDashboard() {
    PomodoroTheme {
        Dashboard()
    }
}
