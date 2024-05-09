package com.kostyarazboynk.pokemon_data.mapper

import com.kostyarazboynik.domain.model.Pokemon
import com.kostyarazboynik.pokemon_api.model.dto.PokemonDto

internal fun PokemonDto.toPokemon(): Pokemon =
    Pokemon(
        name = name,
        url = url,
    )
