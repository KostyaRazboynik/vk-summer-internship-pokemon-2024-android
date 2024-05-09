package com.kostyarazboynk.pokemon_data.repository

import com.kostyarazboynik.domain.model.RequestResult
import com.kostyarazboynik.domain.model.pokemon.PokemonDetails
import com.kostyarazboynik.domain.repository.LoadPokemonDetailsRepository
import com.kostyarazboynik.pokemon_api.PokemonApi
import com.kostyarazboynk.pokemon_data.mapper.toPokemonDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadPokemonDetailsRepositoryImpl@Inject constructor(
    private val api: PokemonApi,
) : LoadPokemonDetailsRepository {

    override suspend fun loadPokemon(id: Int): Flow<RequestResult<PokemonDetails>> = flow {
        val requestResult = api.loadPokemon(id)
        emit(
            if (requestResult.isSuccess) {
                RequestResult.Success(requestResult.getOrThrow().toPokemonDetails())
            } else {
                RequestResult.Error(requestResult.exceptionOrNull())
            }
        )
    }
}
