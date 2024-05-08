package com.kostyarazboynik.pokemon

import android.app.Application
import android.os.SystemClock
import com.kostyarazboynik.utils.Logger
import com.kostyarazboynik.utils.timer.StartTimeHolder
import com.kostyarazboynik.feature_pokemon_details.dagger.FeaturePokemonDetailsUiComponent
import com.kostyarazboynik.feature_pokemon_details.dagger.FeaturePokemonDetailsUiComponentProvider
import com.kostyarazboynik.feature_pokemon_list.dagger.FeaturePokemonListUiComponent
import com.kostyarazboynik.feature_pokemon_list.dagger.FeaturePokemonListUiComponentProvider
import com.kostyarazboynik.pokemon.dagger.AppComponent

class PokemonApp :
    Application(),
    FeaturePokemonListUiComponentProvider,
    FeaturePokemonDetailsUiComponentProvider {

    private var isInitialized = false

    override fun onCreate() {
        super.onCreate()
        Logger.setIsDebug(BuildConfig.DEBUG)
        Logger.d(TAG, "Start time is ${StartTimeHolder.timer.elapsed()}ms")

        appStartTime = SystemClock.elapsedRealtime()

        initialize()
    }

    private fun initialize() {
        if (isInitialized) {
            Logger.d(TAG, "already initialized")
            return
        }
        Logger.d(TAG, "initializing")
        isInitialized = true
        AppComponent.init(this)
        AppComponent.component.inject(this)
    }

    override fun provideFeaturePokemonDetailsUiComponent(): FeaturePokemonDetailsUiComponent =
        AppComponent.component.featurePokemonDetailsUiComponent().create()

    override fun provideFeaturePokemonListUiComponent(): FeaturePokemonListUiComponent =
        AppComponent.component.featurePokemonListUiComponent().create()

    companion object {
        private const val TAG = "PokemonApp"
        var appStartTime = 0L
            private set
    }
}
