package com.kostyarazboynik.domain.mapper

import com.kostyarazboynik.domain.model.RequestResult
import com.kostyarazboynik.domain.model.UiState

internal fun <T : Any> RequestResult<T>.toUiState(): UiState<T> {
    return when (this) {
        is RequestResult.Success -> UiState.Success(data)
        is RequestResult.InProgress -> UiState.Loading
        is RequestResult.Error -> UiState.Error(error?.message)
    }
}
