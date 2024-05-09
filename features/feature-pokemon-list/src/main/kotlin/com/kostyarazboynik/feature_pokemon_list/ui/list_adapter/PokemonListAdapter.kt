package com.kostyarazboynik.feature_pokemon_list.ui.list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.kostyarazboynik.domain.model.pokemon.Pokemon
import com.kostyarazboynik.feature_pokemon_list.databinding.PokemonListItemLayoutBinding
import com.kostyarazboynik.feature_pokemon_list.ui.list_adapter.diffutil.PokemonListDiffUtilCallback

class PokemonListAdapter(
    private val onPokemonClickCallBack: (Pokemon, String?) -> Unit,
) : PagingDataAdapter<Pokemon, PokemonListViewHolder>(PokemonListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder =
        PokemonListViewHolder(
            PokemonListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.apply {
            pokemon?.let {
                val picture = bind(pokemon)
                itemView.setOnClickListener {
                    onPokemonClickCallBack(pokemon, picture)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            NETWORK_VIEW_TYPE
        } else {
            PRODUCT_VIEW_TYPE
        }
    }

    companion object {
        const val NETWORK_VIEW_TYPE = 2
        const val PRODUCT_VIEW_TYPE = 1
    }
}
