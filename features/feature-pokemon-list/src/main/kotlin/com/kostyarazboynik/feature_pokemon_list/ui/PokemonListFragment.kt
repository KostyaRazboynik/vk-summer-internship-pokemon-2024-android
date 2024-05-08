package com.kostyarazboynik.feature_pokemon_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kostyarazboynik.feature_pokemon_details.databinding.FragmentPokemonDetailsLayoutBinding
import com.kostyarazboynik.feature_pokemon_list.dagger.FeaturePokemonListUiComponentProvider

class PokemonListFragment: Fragment() {

    private val viewModel: PokemonListViewModel by lazy {
        (context?.applicationContext as FeaturePokemonListUiComponentProvider)
            .provideFeaturePokemonListUiComponent().getViewModel()
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
        private const val TAG = "PokemonListFragment"
        private const val FRAME_CONTENT_ID = "frame_content_id"

        fun newInstance(
            frameContentId: Int,
        ) = PokemonListFragment().apply {
            arguments = Bundle().apply {
                putInt(FRAME_CONTENT_ID, frameContentId)
            }
        }
    }
}