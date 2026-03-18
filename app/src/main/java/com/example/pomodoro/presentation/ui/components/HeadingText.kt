package com.example.pomodoro.presentation.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.pomodoro.presentation.theme.PomodoroTheme

@Composable
public fun HeadingText(
    content: String,
) {
    Text(
        text = content,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = Bold,
        fontSize = 20.sp
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewText() {
    PomodoroTheme() {
        HeadingText("This is title text")
    }
}