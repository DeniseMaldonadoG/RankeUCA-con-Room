package com.pdm.rankeuca.screen.Resultados

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultadosScreen(
    onVolver: () -> Unit,
    viewModel: ResultadosViewModel = viewModel()
) {
    val opciones = viewModel.opciones
    val isLoading = viewModel.isLoading
    val error = viewModel.error

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Resultados") }
            )
        },
        bottomBar = {
            Button(
                onClick = onVolver,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Volver al menu")
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                error != null -> Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center).padding(16.dp)
                )
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {

                        item {
                            TextButton(
                                onClick = { viewModel.cargarResultados() },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("dar refresh")
                            }
                        }
                        items(opciones) { opcion ->
                            ResultadoItem(
                                nombre = opcion.name,
                                votos = opcion.votes
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResultadoItem(
    nombre: String,
    votos: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = nombre, style = MaterialTheme.typography.bodyLarge)
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$votos",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(text = "votos", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
