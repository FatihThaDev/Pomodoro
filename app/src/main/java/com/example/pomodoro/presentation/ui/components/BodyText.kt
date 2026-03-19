package com.example.pomodoro.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.pomodoro.presentation.theme.PomodoroTheme


@Composable
fun BodyText(
    content: String,
) {
    Text(
        text = content,
        color = MaterialTheme.colorScheme.secondary,
        fontWeight = Bold,
        fontSize = 16.sp
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBodyText() {
    Column {
    PomodoroTheme {
        HeadingText("This is title text")
        BodyText("This is body text")
    }
    }
}