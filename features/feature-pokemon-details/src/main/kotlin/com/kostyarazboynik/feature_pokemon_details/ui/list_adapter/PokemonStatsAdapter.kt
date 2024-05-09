package com.kostyarazboynik.feature_pokemon_details.ui.list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kostyarazboynik.domain.model.pokemon.Stats
import com.kostyarazboynik.feature_pokemon_details.databinding.PokemonStatListItemLayoutBinding
import com.kostyarazboynik.feature_pokemon_details.ui.list_adapter.diffutil.PokemonStatsDiffUtilCallback

class PokemonStatsAdapter :
    ListAdapter<Stats, PokemonStatsViewHolder>(PokemonStatsDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonStatsViewHolder =
        PokemonStatsViewHolder(
            PokemonStatListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: PokemonStatsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
