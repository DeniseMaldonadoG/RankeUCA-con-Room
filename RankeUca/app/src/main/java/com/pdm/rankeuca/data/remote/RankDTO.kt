package com.pdm.rankeuca.data.remote

import kotlinx.serialization.Serializable
import com.pdm.rankeuca.data.remote.Model.votos
import com.pdm.rankeuca.data.model.Option
import kotlin.Int

@Serializable
data class RankDTO (

    val id: Int,
    val name: String,
    val imageUrl: String,
    val votes: Int,

    )

private const val IMAGE_BASE_URL= "https://placehold.co/400x300"


fun RankDTO.toModel(): votos {
    return votos(
        id = id,
        name = name,
        imageUrl = imageUrl,
        votes = votes
    )
}



/*fun RankDTO.toModel(): Option {
    return Option(
        id = id,
        name = name,
    imageUrl = imageUrl
    )

}*/
