package com.example.pomodoro.presentation.ui.screens.donate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.twotone.Call
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pomodoro.presentation.theme.PomodoroTheme
import com.example.pomodoro.presentation.ui.components.BodyText
import com.example.pomodoro.presentation.ui.components.HeadingText
import com.example.pomodoro.presentation.ui.components.ListItem

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
                .padding(vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
                ListItem(icon = Icons.Default.Email, label = "PayPal", value = "paypal@example.com")
                ListItem(icon = Icons.Default.Face, label = "Bitcoin", value = "1A2B3C4D5E6F")
                ListItem(icon = Icons.Default.Favorite, label = "Patreon", value = "patreon.com/FatihTheDev")
        }

        BodyText("Donate to a charity of your choice:")

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(CornerSize(20.dp)))
        ) {
            item {
                ListItem(icon = Icons.TwoTone.CheckCircle, label = "Red Cross", value = "For emergency assistance")

            }
            item {
                ListItem(icon = Icons.TwoTone.Favorite, label = "Oxfam", value = "For inequality and political repression")
            }
            item {
                ListItem(icon = Icons.TwoTone.Call, label = "UNICEF", value = "For disaster relief and emergency aid")
            }
            item {
                ListItem(icon = Icons.TwoTone.Place, label = "Core", value = "For disaster relief")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewDonate() {
    PomodoroTheme {
        Donate()
    }
}
