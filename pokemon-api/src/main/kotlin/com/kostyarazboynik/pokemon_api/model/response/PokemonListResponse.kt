package com.kostyarazboynik.pokemon_api.model.response

import com.kostyarazboynik.pokemon_api.model.dto.PokemonDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
    @SerialName("results") val pokemonList: List<PokemonDto>,
)
