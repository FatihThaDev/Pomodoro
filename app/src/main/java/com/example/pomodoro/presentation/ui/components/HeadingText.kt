package com.example.pomodoro.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodoro.presentation.theme.PomodoroTheme

@Composable
fun HeadingText(
    content: String
) {
    Text(
        text = content,
        color = MaterialTheme.colorScheme.secondary,
        fontWeight = Bold,
        fontSize = 30.sp,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHeadingText() {
    PomodoroTheme {
        HeadingText("This is title text")
    }
}