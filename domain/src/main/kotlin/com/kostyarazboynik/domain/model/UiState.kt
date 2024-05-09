package com.kostyarazboynik.domain.model

sealed interface UiState<out T> {
    data object Initial : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data object Loading : UiState<Nothing>
    data class Error(val cause: String? = null) : UiState<Nothing>
}
