package com.pdm.rankeuca.data.remote



import com.pdm.rankeuca.data.remote.Model.votos


interface RankURepository {
    suspend fun getOpciones(): List<votos>
    suspend fun votar(optionId: Int): Boolean
}