package com.kostyarazboynik.domain.usecase

import androidx.paging.PagingData
import com.kostyarazboynik.domain.model.Pokemon
import com.kostyarazboynik.domain.repository.LoadPokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadPokemonUseCase@Inject constructor(
    private val repository: LoadPokemonRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Pokemon>> = repository.loadPokemon()
}
