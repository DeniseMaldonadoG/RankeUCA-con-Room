package com.pdm.rankeuca.data.model


import com.pdm.rankeuca.data.database.entities.OptionEntity



data class Option(
        val id: Int = 0,
        val name: String,
        val imageUrl: String,
        val questionId: Int = 0,
)

fun Option.toEntity(): OptionEntity {
        return OptionEntity(
                id = id,
                name = name,
                imageUrl = imageUrl,
                questionId = questionId,
        )
}