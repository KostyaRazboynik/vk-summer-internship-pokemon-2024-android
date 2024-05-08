package com.kostyarazboynik.feature_pokemon_details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kostyarazboynik.feature_pokemon_details.dagger.FeaturePokemonDetailsUiComponentProvider
import com.kostyarazboynik.feature_pokemon_details.databinding.FragmentPokemonDetailsLayoutBinding

class PokemonDetailsFragment: Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        private const val TAG = "PokemonDetailsFragment"
        private const val POKEMON_LIST_ITEM_KEY = "pokemon_list_item"

        fun newInstance() = PokemonDetailsFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}