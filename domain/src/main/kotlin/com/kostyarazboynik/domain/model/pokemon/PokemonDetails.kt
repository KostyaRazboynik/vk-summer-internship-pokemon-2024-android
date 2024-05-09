package com.kostyarazboynik.domain.model.pokemon

data class PokemonDetails(
    val sprites: Sprites,
    val stats: List<Stats>?,
    val height: Int,
    val weight: Int,
)
