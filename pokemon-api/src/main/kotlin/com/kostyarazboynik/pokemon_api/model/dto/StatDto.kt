package com.kostyarazboynik.pokemon_api.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatDto(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)
