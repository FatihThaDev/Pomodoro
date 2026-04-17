package com.example.pomodoro.presentation.ui.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pomodoro.model.projectsList
import com.example.pomodoro.presentation.theme.PomodoroTheme
import com.example.pomodoro.presentation.ui.components.BodyText
import com.example.pomodoro.presentation.ui.components.HeadingText
import com.example.pomodoro.presentation.ui.components.ListItem
import com.example.pomodoro.presentation.ui.screens.about.util.filterProjects

@Composable
private fun AboutScreen(
    searchQuery: String,
    valueChange: (String) -> Unit,
    onProjectClick: (String, String) -> Unit
) {
    val filteredProjects = remember(searchQuery) {
        filterProjects(projectsList, searchQuery)
    }

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

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {
                    ListItem(
                        icon = Icons.Default.Person,
                        label = "Developer",
                        value = "FatihTheDev"
                    )
                }
                item {
                    ListItem(icon = Icons.Default.Info, label = "Version", value = "1.0.0")
                }
                item {
                    ListItem(icon = Icons.Default.Build, label = "License", value = "MIT")
                }
            }

        BodyText("My Other Projects:")

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { valueChange(it) },
            placeholder = { Text("Search projects...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            singleLine = true
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(CornerSize(20.dp)))
        ) {
            if (filteredProjects.isEmpty()) {
                item {
                    Text(
                        text = "No projects with that name found",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 40.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
            else {
                items(filteredProjects) { project ->
                    ListItem(
                        icon = project.icon,
                        label = project.label,
                        value = project.value,
                        onClick = { onProjectClick(project.label, project.value) }
                    )
                }
            }
        }
    }
}

@Composable
fun About(onProjectClick: (String, String) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }

    AboutScreen(
        searchQuery = searchQuery,
        valueChange = {
            searchQuery = it
        },
        onProjectClick = onProjectClick
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewAbout() {
    PomodoroTheme {
        About { _, _ -> }
    }
}
