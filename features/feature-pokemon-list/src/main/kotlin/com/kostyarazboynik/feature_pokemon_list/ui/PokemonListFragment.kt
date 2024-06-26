package com.kostyarazboynik.feature_pokemon_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.kostyarazboynik.domain.model.pokemon.Pokemon
import com.kostyarazboynik.feature_pokemon_details.ui.PokemonDetailsFragment
import com.kostyarazboynik.feature_pokemon_list.dagger.FeaturePokemonListUiComponentProvider
import com.kostyarazboynik.feature_pokemon_list.databinding.FragmentPokemonListLayoutBinding
import com.kostyarazboynik.feature_pokemon_list.ui.list_adapter.LoadingStateAdapter
import com.kostyarazboynik.feature_pokemon_list.ui.list_adapter.PokemonListAdapter
import com.kostyarazboynik.utils.extensions.launchNamed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

class PokemonListFragment : Fragment() {

    private val viewModel: PokemonListViewModel by lazy {
        (context?.applicationContext as FeaturePokemonListUiComponentProvider)
            .provideFeaturePokemonListUiComponent().getViewModel()
    }

    private lateinit var binding: FragmentPokemonListLayoutBinding

    private val listAdapter = PokemonListAdapter { pokemon, picture ->
        navigateToMovieDetailsFragment(pokemon, picture)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPokemonListLayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpSwipeRefreshListener()
        loadData(false)
    }

    private fun navigateToMovieDetailsFragment(pokemon: Pokemon, picture: String?) {
        activity?.supportFragmentManager?.let { manager ->
            arguments?.getInt(FRAME_CONTENT_ID)?.let { id ->
                val transaction = manager.beginTransaction()
                val fragment = PokemonDetailsFragment.newInstance(pokemon, picture)
                transaction.replace(id, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.apply {
            recyclerView.apply {
                adapter = listAdapter.apply {
                    withLoadStateFooter(
                        footer = LoadingStateAdapter { listAdapter.retry() }
                    )
                    addLoadStateListener { loadState ->
                        if (loadState.refresh is LoadState.Loading &&
                            listAdapter.snapshot().isEmpty()
                        ) {
                            binding.progressBar.isVisible = true
                        } else {
                            binding.progressBar.isVisible = false
                            binding.swipeRefreshLayout.isRefreshing = false
                        }
                    }
                }
                layoutManager = GridLayoutManager(requireContext(), 2).apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            val viewType = listAdapter.getItemViewType(position)
                            return if (viewType == PokemonListAdapter.PRODUCT_VIEW_TYPE) {
                                PokemonListAdapter.PRODUCT_VIEW_TYPE
                            } else {
                                PokemonListAdapter.NETWORK_VIEW_TYPE
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setUpSwipeRefreshListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            loadData(true)
        }
    }

    private fun loadData(submitEmpty: Boolean) {
        viewModel.loadData()
        viewModel.viewModelScope.launchNamed("$TAG-viewModelScope-loadData", Dispatchers.Default) {
            if (submitEmpty) listAdapter.submitData(PagingData.empty())
            viewModel.pagingDataStateFlow.collectLatest {
                listAdapter.submitData(it)
            }
        }
    }

    companion object {
        private const val TAG = "PokemonListFragment"
        private const val FRAME_CONTENT_ID = "frame_content_id"

        fun newInstance(
            frameContentId: Int,
        ) = PokemonListFragment().apply {
            arguments = Bundle().apply { putInt(FRAME_CONTENT_ID, frameContentId) }
        }
    }
}
