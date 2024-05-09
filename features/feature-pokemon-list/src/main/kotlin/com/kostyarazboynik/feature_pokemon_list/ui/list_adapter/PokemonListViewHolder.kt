package com.kostyarazboynik.feature_pokemon_list.ui.list_adapter

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kostyarazboynik.domain.model.Pokemon
import com.kostyarazboynik.feature_pokemon_list.R
import com.kostyarazboynik.feature_pokemon_list.databinding.PokemonListItemLayoutBinding
import com.kostyarazboynik.feature_pokemon_list.ui.list_adapter.utils.getPicUrl
import java.util.Locale

class PokemonListViewHolder(
    private val binding: PokemonListItemLayoutBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pokemon: Pokemon) {
        binding.apply {
            pokemonTitle.text = pokemon.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                else it.toString()
            }
            loadImage(pokemon)
        }
    }

    private fun loadImage(pokemon: Pokemon) {
        val picture = pokemon.url.getPicUrl()

        binding.apply {
            Glide.with(root)
                .load(picture)
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean,
                    ): Boolean {
                        progressCircular.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean,
                    ): Boolean {
                        Palette.Builder((resource as BitmapDrawable).bitmap).generate {
                            it?.let { palette ->
                                pokemonImage.setBackgroundColor(
                                    palette.getDominantColor(
                                        ContextCompat.getColor(
                                            root.context,
                                            R.color.white,
                                        ),
                                    ),
                                )
                            }
                        }
                        progressCircular.isVisible = false
                        return false
                    }
                })
                .into(pokemonImage)
        }
    }
}
