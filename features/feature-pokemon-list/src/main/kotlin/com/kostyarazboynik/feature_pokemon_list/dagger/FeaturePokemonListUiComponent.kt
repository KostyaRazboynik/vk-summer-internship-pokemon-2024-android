package com.kostyarazboynik.feature_pokemon_list.dagger

import com.kostyarazboynik.dagger.FeatureScope
import com.kostyarazboynik.feature_pokemon_list.ui.PokemonListViewModel
import dagger.Subcomponent

/**
 * Dagger component for pokemon list feature
 */
@Subcomponent
@FeatureScope
interface FeaturePokemonListUiComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FeaturePokemonListUiComponent
    }

    fun getViewModel(): PokemonListViewModel
}

interface FeaturePokemonListUiComponentProvider {
    fun provideFeaturePokemonListUiComponent(): FeaturePokemonListUiComponent
}
