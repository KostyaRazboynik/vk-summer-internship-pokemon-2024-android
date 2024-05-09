package com.kostyarazboynk.pokemon_data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kostyarazboynik.domain.model.pokemon.Pokemon
import com.kostyarazboynik.domain.repository.LoadPokemonRepository
import com.kostyarazboynik.pokemon_api.PokemonApi
import com.kostyarazboynk.pokemon_data.datasource.PokemonDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadPokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi,
) : LoadPokemonRepository {

    override suspend fun loadPokemon(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = { PokemonDataSource(api) }
        ).flow
    }
}
