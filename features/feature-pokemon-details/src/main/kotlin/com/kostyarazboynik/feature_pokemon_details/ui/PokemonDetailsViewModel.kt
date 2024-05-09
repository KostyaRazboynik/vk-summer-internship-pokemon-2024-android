package com.kostyarazboynik.feature_pokemon_details.ui

import androidx.lifecycle.ViewModel
import com.kostyarazboynik.dagger.FeatureScope
import javax.inject.Inject

@FeatureScope
class PokemonDetailsViewModel @Inject constructor(

) : ViewModel() {

    companion object {
        private const val TAG = "PokemonDetailsViewModel"
    }
}
