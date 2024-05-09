package com.kostyarazboynik.domain.repository

import com.kostyarazboynik.domain.model.RequestResult
import com.kostyarazboynik.domain.model.pokemon.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface LoadPokemonDetailsRepository {

    suspend fun loadPokemon(id: Int): Flow<RequestResult<PokemonDetails>>
}
