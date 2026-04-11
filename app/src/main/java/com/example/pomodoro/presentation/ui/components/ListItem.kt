package com.example.pomodoro.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListItem(
    icon: ImageVector? = null,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
        .clickable(onClick = {})
            .padding(horizontal = 6.dp, vertical = 3.dp)
    ) {
        if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 12.dp)
                )
        }
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = label,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = value,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListItem() {
    ListItem(icon = null, label = "This is a label", value = "This is a value")
}