package com.kostyarazboynik.feature_pokemon_details.ui.list_adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.kostyarazboynik.domain.model.pokemon.Stats

class PokemonStatsDiffUtilCallback : DiffUtil.ItemCallback<Stats>() {

    override fun areItemsTheSame(
        oldItem: Stats,
        newItem: Stats
    ): Boolean = areContentsTheSame(oldItem, newItem)

    override fun areContentsTheSame(
        oldItem: Stats,
        newItem: Stats
    ): Boolean = oldItem == newItem
}
