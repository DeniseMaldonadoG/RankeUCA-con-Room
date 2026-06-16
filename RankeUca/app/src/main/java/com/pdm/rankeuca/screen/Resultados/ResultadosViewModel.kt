package com.pdm.rankeuca.screen.Resultados


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.rankeuca.data.remote.RankRepository
import com.pdm.rankeuca.data.remote.Model.votos
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ResultadosViewModel(
    private val repository: RankRepository = RankRepository()
) : ViewModel() {

    var opciones by mutableStateOf<List<votos>>(emptyList())
        private set
    var isLoading by mutableStateOf(false)
        private set
    var error by mutableStateOf<String?>(null)
        private set

    init {
        cargarResultados()
    }

    fun cargarResultados() {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {

                opciones = repository.getOpciones()
                    .sortedByDescending { it.votes }
            } catch (e: Exception) {
                error = "Error al cargar resultados: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}