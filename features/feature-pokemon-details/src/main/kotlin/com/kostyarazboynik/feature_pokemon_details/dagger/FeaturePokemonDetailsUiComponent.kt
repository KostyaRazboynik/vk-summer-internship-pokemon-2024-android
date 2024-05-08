package com.kostyarazboynik.feature_pokemon_details.dagger

import com.kostyarazboynik.dagger.FeatureScope
import com.kostyarazboynik.feature_pokemon_details.ui.PokemonDetailsViewModel
import dagger.Subcomponent

/**
 * Dagger component for pokemon details feature
 */
@Subcomponent
@FeatureScope
interface FeaturePokemonDetailsUiComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FeaturePokemonDetailsUiComponent
    }

    fun getViewModel(): PokemonDetailsViewModel
}

interface FeaturePokemonDetailsUiComponentProvider {
    fun provideFeaturePokemonDetailsUiComponent(): FeaturePokemonDetailsUiComponent
}
