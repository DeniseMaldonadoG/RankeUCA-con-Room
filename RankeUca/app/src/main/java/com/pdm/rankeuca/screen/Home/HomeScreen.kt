package com.pdm.rankeuca.screen.Home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onIrResultados: () -> Unit,
    onIrOptions: () -> Unit,
    onIrQuestions: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val opciones = viewModel.opciones
    val isLoading = viewModel.isLoading
    val error = viewModel.error
    val optionVotada = viewModel.optionVotada

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("RankeUCA - Votá") }
            )
        },
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Button(
                    onClick = onIrResultados,
                    enabled = optionVotada != null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ir a resultados →")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onIrOptions,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ir a opciones →")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onIrQuestions,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ir a preguntas →")
                }
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
                        items(opciones) { opcion ->
                            OpcionItem(
                                nombre = opcion.name,
                                imagenUrl = opcion.imageUrl,
                                seleccionado = opcion.id == optionVotada,
                                onClick = { viewModel.votar(opcion.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OpcionItem(
    nombre: String,
    imagenUrl: String,
    seleccionado: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imagenUrl,
                contentDescription = nombre,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = nombre,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )

            if (seleccionado) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Votado",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
