package com.kostyarazboynk.pokemon_data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kostyarazboynik.domain.model.pokemon.Pokemon
import com.kostyarazboynik.pokemon_api.PokemonApi
import com.kostyarazboynik.utils.Logger
import com.kostyarazboynk.pokemon_data.mapper.toPokemon

class PokemonDataSource(
    private val api: PokemonApi,
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val offset = params.key ?: STARTING_OFFSET_INDEX
        val loadSize = params.loadSize
        val requestResult = api.loadPokemons(loadSize, offset)
        Logger.d(TAG, requestResult.toString())

        return if (requestResult.isSuccess) {
            val data = requestResult.getOrThrow()
            LoadResult.Page(
                data = data.pokemonList.map { it.toPokemon() },
                prevKey = if (offset == STARTING_OFFSET_INDEX) null else offset - loadSize,
                nextKey = if (data.next == null) null else offset + loadSize
            )
        } else {
            LoadResult.Error(requestResult.exceptionOrNull() ?: Throwable("Unknown exception"))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? = state.anchorPosition

    private companion object {
        private const val TAG = "PokemonDataSource"
        private const val STARTING_OFFSET_INDEX = 0
    }
}
