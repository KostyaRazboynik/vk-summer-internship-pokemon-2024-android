package com.kostyarazboynk.pokemon_data.mapper

import com.kostyarazboynik.domain.model.pokemon.Pokemon
import com.kostyarazboynik.domain.model.pokemon.PokemonDetails
import com.kostyarazboynik.domain.model.pokemon.Sprites
import com.kostyarazboynik.domain.model.pokemon.Stat
import com.kostyarazboynik.domain.model.pokemon.Stats
import com.kostyarazboynik.pokemon_api.model.dto.PokemonDto
import com.kostyarazboynik.pokemon_api.model.dto.SpritesDto
import com.kostyarazboynik.pokemon_api.model.dto.StatDto
import com.kostyarazboynik.pokemon_api.model.dto.StatsDto
import com.kostyarazboynik.pokemon_api.model.response.PokemonResponse

internal fun PokemonDto.toPokemon(): Pokemon =
    Pokemon(
        name = name,
        url = url,
    )

internal fun PokemonResponse.toPokemonDetails(): PokemonDetails =
    PokemonDetails(
        sprites = sprites.toSprites(),
        stats = stats?.let { list -> list.map { it.toStats() } },
        height = height,
        weight = weight,
    )

private fun SpritesDto.toSprites(): Sprites =
    Sprites(
        backDefault = backDefault,
        backShiny = backShiny,
        frontDefault = frontDefault,
        frontShiny = frontShiny,
    )

private fun StatsDto.toStats(): Stats =
    Stats(
        baseStat = baseStat,
        effort = effort,
        stat = stat.toStat(),
    )

private fun StatDto.toStat(): Stat =
    Stat(
        name = name,
        url = url,
    )
