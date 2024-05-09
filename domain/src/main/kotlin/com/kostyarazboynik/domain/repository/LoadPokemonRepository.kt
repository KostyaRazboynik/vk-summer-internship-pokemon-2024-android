package com.kostyarazboynik.domain.repository

import androidx.paging.PagingData
import com.kostyarazboynik.domain.model.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface LoadPokemonRepository {

    suspend fun loadPokemon(): Flow<PagingData<Pokemon>>
}
