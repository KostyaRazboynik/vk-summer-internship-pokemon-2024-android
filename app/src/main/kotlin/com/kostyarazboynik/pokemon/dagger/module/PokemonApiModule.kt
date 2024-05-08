package com.kostyarazboynik.pokemon.dagger.module

import com.kostyarazboynik.pokemon.BuildConfig
import com.kostyarazboynik.pokemon_api.PokemonApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
object PokemonApiModule {

    @Provides
    @Singleton
    fun provideMoviesApi(okHttpClient: OkHttpClient?): PokemonApi =
        PokemonApi(
            baseUrl = BuildConfig.BASE_URL,
            okHttpClient = okHttpClient,
        )
}
