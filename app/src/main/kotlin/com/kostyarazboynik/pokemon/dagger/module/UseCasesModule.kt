package com.kostyarazboynik.pokemon.dagger.module

import com.kostyarazboynik.domain.repository.LoadPokemonRepository
import com.kostyarazboynik.domain.usecase.LoadPokemonUseCase
import dagger.Module
import dagger.Provides

@Module
object UseCasesModule {

    @Provides
    fun provideLoadPokemonUseCase(loadPokemonRepository: LoadPokemonRepository): LoadPokemonUseCase =
        LoadPokemonUseCase(loadPokemonRepository)
}
