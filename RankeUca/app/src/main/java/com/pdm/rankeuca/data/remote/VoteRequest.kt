package com.pdm.rankeuca.data.remote


import kotlinx.serialization.Serializable

@Serializable
data class VotoRequest(
    val optionId: Int
)