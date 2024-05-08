package com.kostyarazboynik.pokemon_api.model.response

import com.kostyarazboynik.pokemon_api.model.dto.Sprites
import com.kostyarazboynik.pokemon_api.model.dto.StatsDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    @SerialName("sprites") val sprites: Sprites,
    @SerialName("stats") val stats: List<StatsDto>?,
    @SerialName("height") val height: Int,
    @SerialName("weight") val weight: Int,
)