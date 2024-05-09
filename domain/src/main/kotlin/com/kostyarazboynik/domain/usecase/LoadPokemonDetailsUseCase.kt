package com.kostyarazboynik.domain.usecase

import com.kostyarazboynik.domain.mapper.toUiState
import com.kostyarazboynik.domain.model.UiState
import com.kostyarazboynik.domain.model.pokemon.PokemonDetails
import com.kostyarazboynik.domain.repository.LoadPokemonDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadPokemonDetailsUseCase @Inject constructor(
    private val repository: LoadPokemonDetailsRepository
) {
    suspend operator fun invoke(id: Int): Flow<UiState<PokemonDetails>> =
        repository.loadPokemon(id).map { it.toUiState() }
}
