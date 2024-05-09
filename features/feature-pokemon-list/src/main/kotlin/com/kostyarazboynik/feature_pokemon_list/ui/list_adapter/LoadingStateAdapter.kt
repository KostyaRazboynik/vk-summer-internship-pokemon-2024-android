package com.kostyarazboynik.feature_pokemon_list.ui.list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kostyarazboynik.feature_pokemon_list.databinding.NetworkStateItemLayoutBinding
import com.kostyarazboynik.utils.Logger

class LoadingStateAdapter(private val retryCallBack: () -> Unit) :
    LoadStateAdapter<LoadingStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        with(holder.binding) {
            progressBar.isVisible = loadState is LoadState.Loading
            errorMessage.isVisible = loadState is LoadState.Error
            retry.isVisible = loadState is LoadState.Error

            if (loadState is LoadState.Error) {
                errorMessage.text = loadState.error.localizedMessage
            }
            retry.setOnClickListener {
                retryCallBack()
            }
        }

        Logger.d(TAG, loadState.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder =
        LoadStateViewHolder(
            NetworkStateItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )

    class LoadStateViewHolder(val binding: NetworkStateItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private companion object {
        private const val TAG = "LoadingStateAdapter"
    }
}
