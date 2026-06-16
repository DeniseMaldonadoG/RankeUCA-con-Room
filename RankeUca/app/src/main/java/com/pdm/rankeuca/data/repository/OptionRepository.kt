package com.pdm.rankeuca.data.repository

import com.pdm.rankeuca.data.remote.KtorClient
import com.pdm.rankeuca.data.remote.RankDTO
import com.pdm.rankeuca.data.remote.VotoRequest
import com.pdm.rankeuca.data.remote.toModel
import com.pdm.rankeuca.data.model.Option
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow

interface OptionRepository {

    fun getOptions(questionId: Int): Flow<List<Option>>
    suspend fun addOption(name: String, imageUrl: String, questionId: Int)
    suspend fun deleteOption(option: Option)
}






