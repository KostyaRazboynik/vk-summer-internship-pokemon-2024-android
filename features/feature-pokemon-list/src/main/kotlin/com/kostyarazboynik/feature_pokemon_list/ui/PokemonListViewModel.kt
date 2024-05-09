package com.kostyarazboynik.feature_pokemon_list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kostyarazboynik.dagger.FeatureScope
import com.kostyarazboynik.domain.model.Pokemon
import com.kostyarazboynik.domain.usecase.LoadPokemonUseCase
import com.kostyarazboynik.utils.extensions.launchNamed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

@FeatureScope
class PokemonListViewModel @Inject constructor(
    private val loadPokemonUseCase: LoadPokemonUseCase,
) : ViewModel() {

    private val _pagingDataStateFlow: MutableStateFlow<PagingData<Pokemon>> =
        MutableStateFlow(PagingData.empty())
    val pagingDataStateFlow: StateFlow<PagingData<Pokemon>> = _pagingDataStateFlow

    fun loadData() {
        viewModelScope.launchNamed("$TAG-viewModelScope-observeNetwork", Dispatchers.IO) {
            _pagingDataStateFlow.emitAll(loadPokemonUseCase().cachedIn(viewModelScope))
        }
    }

    private companion object {
        private const val TAG = "PokemonListViewModel"
    }
}
