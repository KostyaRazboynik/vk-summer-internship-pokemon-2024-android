package com.kostyarazboynik.pokemon.dagger.module

import com.kostyarazboynik.domain.repository.LoadPokemonDetailsRepository
import com.kostyarazboynik.domain.repository.LoadPokemonRepository
import com.kostyarazboynk.pokemon_data.repository.LoadPokemonDetailsRepositoryImpl
import com.kostyarazboynk.pokemon_data.repository.LoadPokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface RepositoryModule {

    @Reusable
    @Binds
    fun bindLoadPokemonRepository(
        loadPokemonRepository: LoadPokemonRepositoryImpl,
    ): LoadPokemonRepository

    @Reusable
    @Binds
    fun bindLoadPokemonDetailsRepository(
        loadPokemonDetailsRepository: LoadPokemonDetailsRepositoryImpl,
    ): LoadPokemonDetailsRepository
}
