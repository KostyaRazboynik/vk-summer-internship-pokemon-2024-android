package com.kostyarazboynik.feature_pokemon_list.ui.list_adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.kostyarazboynik.domain.model.pokemon.Pokemon

class PokemonListDiffUtilCallback : DiffUtil.ItemCallback<Pokemon>() {

    override fun areItemsTheSame(
        oldItem: Pokemon,
        newItem: Pokemon,
    ): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: Pokemon,
        newItem: Pokemon,
    ): Boolean =
        oldItem == newItem
}
