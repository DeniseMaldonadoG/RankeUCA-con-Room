package com.pdm.rankeuca.screen.Home



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.rankeuca.data.remote.RankRepository
import com.pdm.rankeuca.data.remote.Model.votos
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class HomeViewModel(
    private val repository: RankRepository = RankRepository()
) : ViewModel() {

    var opciones by mutableStateOf<List<votos>>(emptyList())
        private set
    var isLoading by mutableStateOf(false)
        private set
    var error by mutableStateOf<String?>(null)
        private set
    var optionVotada by mutableStateOf<Int?>(null)
        private set

    init {
        cargarOpciones()
    }

    fun cargarOpciones() {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                opciones = repository.getOpciones()
            } catch (e: Exception) {
                error = "Error al cargar opciones: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun votar(optionId: Int) {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                val exitoso = repository.votar(optionId)
                if (exitoso) optionVotada = optionId
            } catch (e: Exception) {
                error = "Error al votar: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}