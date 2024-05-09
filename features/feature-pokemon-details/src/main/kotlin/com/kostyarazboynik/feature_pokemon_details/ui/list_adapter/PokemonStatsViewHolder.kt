package com.kostyarazboynik.feature_pokemon_details.ui.list_adapter

import androidx.recyclerview.widget.RecyclerView
import com.kostyarazboynik.domain.model.pokemon.Stats
import com.kostyarazboynik.feature_pokemon_details.databinding.PokemonStatListItemLayoutBinding
import com.kostyarazboynik.utils.extensions.capitalizeEx
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PokemonStatsViewHolder(
    private val binding: PokemonStatListItemLayoutBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(stat: Stats) {
        binding.apply {
            progressBar.apply {
                secondaryProgress = MAX_BASE_STATE
                max = MAX_BASE_STATE
                CoroutineScope(Dispatchers.Main).launch {
                    var state = 0
                    while (state <= stat.baseStat) {
                        progress = state
                        state++
                        delay(PROGRESS_BAR_ANIMATION_DURATION_MS)
                    }
                }
            }

            statName.text = stat.stat.name.capitalizeEx()

            if (stat.stat.name.contains("-")) {
                val first = stat.stat.name.substringBefore("-").capitalizeEx()
                val second = stat.stat.name.substringAfter("-").capitalizeEx()

                "$first - $second".also { statName.text = it }
            }
            statCount.text = stat.baseStat.toString()
        }
    }

    private companion object {
        private const val PROGRESS_BAR_ANIMATION_DURATION_MS = 20L
        private const val MAX_BASE_STATE = 255
    }
}
