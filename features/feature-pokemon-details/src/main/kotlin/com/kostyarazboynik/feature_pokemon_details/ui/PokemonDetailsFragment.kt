package com.kostyarazboynik.feature_pokemon_details.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kostyarazboynik.domain.model.UiState
import com.kostyarazboynik.domain.model.pokemon.Pokemon
import com.kostyarazboynik.feature_pokemon_details.dagger.FeaturePokemonDetailsUiComponentProvider
import com.kostyarazboynik.feature_pokemon_details.databinding.FragmentPokemonDetailsLayoutBinding
import com.kostyarazboynik.feature_pokemon_details.ui.list_adapter.PokemonStatsAdapter
import com.kostyarazboynik.utils.Logger
import com.kostyarazboynik.utils.extensions.capitalizeEx
import com.kostyarazboynik.utils.extensions.launchNamed
import kotlinx.coroutines.Dispatchers

class PokemonDetailsFragment : Fragment() {

    private val viewModel: PokemonDetailsViewModel by lazy {
        (context?.applicationContext as FeaturePokemonDetailsUiComponentProvider)
            .provideFeaturePokemonDetailsUiComponent().getViewModel()
    }

    private lateinit var binding: FragmentPokemonDetailsLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPokemonDetailsLayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.getParcelable(POKEMON_LIST_ITEM_KEY) as? Pokemon)?.let { pokemon ->
            viewModel.loadPokemon(pokemon.url)
            binding.pokemonName.text = pokemon.name.capitalizeEx()
        }

        arguments?.getString(POKEMON_PICTURE_KEY)?.let { picture ->
            binding.apply {
                Glide.with(root)
                    .load(picture)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(pokemonImage)
            }
        }

        updateUiState()
    }

    @SuppressLint("SetTextI18n")
    private fun updateUiState() {
        viewModel.viewModelScope.launchNamed("$TAG-viewModelScope-loadData", Dispatchers.Main) {
            viewModel.pokemonDetailsUiState.collect {
                when (it) {
                    is UiState.Success -> {
                        Logger.d(TAG, it.data.toString())
                        binding.progressCircular.isVisible = false
                        binding.apply {
                            pokemonWeight.text = "${it.data.weight.div(STATS_DIV_CONST)} kgs"
                            pokemonHeight.text = "${it.data.height.div(STATS_DIV_CONST)} metres"
                            pokemonStatList.adapter = PokemonStatsAdapter().apply {
                                submitList(it.data.stats)
                            }
                        }
                    }

                    is UiState.Error -> binding.progressCircular.isVisible = false
                    is UiState.Loading -> binding.progressCircular.isVisible = true
                    UiState.Initial -> Unit
                }
            }
        }
    }

    companion object {
        private const val TAG = "PokemonDetailsFragment"
        private const val POKEMON_LIST_ITEM_KEY = "pokemon_list_item"
        private const val POKEMON_PICTURE_KEY = "pokemon_picture"

        private const val STATS_DIV_CONST = 10.0

        fun newInstance(
            pokemon: Pokemon,
            picture: String?,
        ) = PokemonDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(POKEMON_LIST_ITEM_KEY, pokemon)
                putString(POKEMON_PICTURE_KEY, picture)
            }
        }
    }
}
