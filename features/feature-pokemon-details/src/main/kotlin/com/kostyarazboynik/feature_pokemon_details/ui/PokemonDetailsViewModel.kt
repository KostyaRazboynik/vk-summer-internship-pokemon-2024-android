package com.kostyarazboynik.feature_pokemon_details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kostyarazboynik.dagger.FeatureScope
import com.kostyarazboynik.domain.model.UiState
import com.kostyarazboynik.domain.model.pokemon.PokemonDetails
import com.kostyarazboynik.domain.usecase.LoadPokemonDetailsUseCase
import com.kostyarazboynik.utils.extensions.extractId
import com.kostyarazboynik.utils.extensions.launchNamed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

@FeatureScope
class PokemonDetailsViewModel @Inject constructor(
    private val loadPokemonDetailsUseCase: LoadPokemonDetailsUseCase,
) : ViewModel() {

    private val _pokemonDetailsUiState: MutableStateFlow<UiState<PokemonDetails>> =
        MutableStateFlow(UiState.Initial)
    val pokemonDetailsUiState: StateFlow<UiState<PokemonDetails>> = _pokemonDetailsUiState

    fun loadPokemon(url: String) {
        viewModelScope.launchNamed("$TAG-viewModelScope-loadPokemon", Dispatchers.IO) {
            _pokemonDetailsUiState.emit(UiState.Loading)
            _pokemonDetailsUiState.emitAll(loadPokemonDetailsUseCase(url.extractId()))
        }
    }

    companion object {
        private const val TAG = "PokemonDetailsViewModel"
    }
}
