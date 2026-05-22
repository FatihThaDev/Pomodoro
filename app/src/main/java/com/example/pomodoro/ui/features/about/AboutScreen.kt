package com.example.pomodoro.ui.features.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pomodoro.ui.theme.PomodoroTheme
import com.example.pomodoro.ui.components.BodyText
import com.example.pomodoro.ui.components.HeadingText
import com.example.pomodoro.ui.components.ListItem
import com.example.pomodoro.ui.features.about.util.Project
import com.example.pomodoro.ui.features.about.util.filterProjects

@Composable
internal fun AboutScreen(
    searchQuery: String,
    valueChange: (String) -> Unit,
    onProjectClick: (String, String) -> Unit,
    version: String = "1.0.0",
    projects: List<Project> = emptyList()
) {
    val filteredProjects = remember(searchQuery, projects) {
        filterProjects(projects, searchQuery)
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                ListItem(
                    icon = Icons.Default.Person,
                    label = "Developer",
                    value = "FatihTheDev"
                )
                ListItem(icon = Icons.Default.Info, label = "Version", value = version)
                ListItem(icon = Icons.Default.Build, label = "License", value = "MIT")
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
                for (project in filteredProjects) {
                    item(key = project.label) {
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
}

@Composable
fun About(onProjectClick: (String, String) -> Unit) {
    val viewModel: AboutViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val projectUiModels = remember(uiState.projects) {
        if (uiState.projects.isEmpty()) {
            listOf(
                Project(label = "FatihNvim", value = "Neovim config for power users"),
                Project(label = "archlinux-install", value = "Arch Linux install script"),
                Project(label = "foodify-delivery-app", value = "Next.js + Tailwind food delivery app"),
                Project(label = "Cave-Slayers", value = "2D action game in Python")
            )
        } else {
            uiState.projects.map { entity ->
                Project(icon = null, label = entity.name, value = entity.description)
            }
        }
    }

    AboutScreen(
        searchQuery = uiState.searchQuery,
        valueChange = { viewModel.onSearchQueryChange(it) },
        onProjectClick = onProjectClick,
        version = uiState.appVersion,
        projects = projectUiModels
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewAbout() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedProject by remember { mutableStateOf<Pair<String, String>?>(null) }

    PomodoroTheme {
        val selected = selectedProject
        if (selected != null) {
            ProjectDetailsScreen(
                label = selected.first,
                description = selected.second,
                onBackClick = { selectedProject = null }
            )
        } else {
            AboutScreen(
                searchQuery = searchQuery,
                valueChange = { searchQuery = it },
                onProjectClick = { label, desc -> selectedProject = label to desc },
                projects = listOf(
                    Project(label = "FatihNvim", value = "Neovim config for power users"),
                    Project(label = "archlinux-install", value = "Arch Linux install script"),
                    Project(label = "foodify-delivery-app", value = "Next.js + Tailwind food delivery app"),
                    Project(label = "Cave-Slayers", value = "2D action game in Python")
                )
            )
        }
    }
}
