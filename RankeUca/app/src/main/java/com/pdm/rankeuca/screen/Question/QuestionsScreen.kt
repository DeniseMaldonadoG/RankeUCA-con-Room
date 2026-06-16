package com.pdm.rankeuca.screen.Question

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionsScreen(
    onQuestionClick: (Int) -> Unit,
    onVolver: () -> Unit,
    viewModel: QuestionsViewModel = viewModel(
        factory = QuestionsViewModel.Factory
    )
) {
    val questions by viewModel.questions.collectAsStateWithLifecycle()

    var showBottomSheet by remember { mutableStateOf(false) }
    var newQuestionTitle by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Preguntas") },
                actions = {
                    IconButton(onClick = { showBottomSheet = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Nueva pregunta")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Volver")
            }
        }
    ) { paddingValues ->
        if (questions.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Todavía no hay preguntas")
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(paddingValues)
            ) {
                items(questions) { question ->
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onQuestionClick(question.id) }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = question.title, style = MaterialTheme.typography.titleMedium)
                            Text(text = "${question.optionCount} opciones")
                            IconButton(onClick = { viewModel.deleteQuestion(question) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = { showBottomSheet = false }) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Nueva pregunta")
                OutlinedTextField(
                    value = newQuestionTitle,
                    onValueChange = { newQuestionTitle = it },
                    label = { Text("Título") }
                )
                Button(
                    onClick = {
                        viewModel.addQuestion(newQuestionTitle)
                        newQuestionTitle = ""
                        showBottomSheet = false
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Guardar")
                }
            }
        }
    }
}
