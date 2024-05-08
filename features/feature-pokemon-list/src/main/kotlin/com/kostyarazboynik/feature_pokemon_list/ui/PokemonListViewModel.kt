package com.kostyarazboynik.feature_pokemon_list.ui

import androidx.lifecycle.ViewModel
import com.kostyarazboynik.dagger.FeatureScope
import javax.inject.Inject

@FeatureScope
class PokemonListViewModel @Inject constructor(

): ViewModel() {

    companion object {
        private const val TAG = "PokemonListViewModel"
    }
}