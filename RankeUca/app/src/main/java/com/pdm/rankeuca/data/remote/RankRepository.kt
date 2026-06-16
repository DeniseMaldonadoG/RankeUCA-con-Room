package com.pdm.rankeuca.data.remote




import com.pdm.rankeuca.data.remote.KtorClient
import com.pdm.rankeuca.data.remote.RankDTO
import com.pdm.rankeuca.data.remote.VotoRequest
import com.pdm.rankeuca.data.remote.toModel
import com.pdm.rankeuca.data.remote.Model.votos
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RankRepository : RankURepository {

    private val client = KtorClient.client
    private val baseUrl = "https://qjcxdvfzyseuvezacxsd.supabase.co/functions/v1/rankeuca"



    override suspend fun getOpciones(): List<votos> {
        return client.get("$baseUrl/options")
            .body<List<RankDTO>>()
            .map { it.toModel() }
    }

    override suspend fun votar(optionId: Int): Boolean {
        val response = client.post("$baseUrl/vote") {
            contentType(ContentType.Application.Json)
            setBody(VotoRequest(optionId = optionId))
        }
        return response.status.value == 200
    }
}