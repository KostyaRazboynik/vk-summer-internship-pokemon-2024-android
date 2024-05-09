package com.kostyarazboynik.domain.model

sealed interface RequestResult<out E : Any> {
    data object InProgress : RequestResult<Nothing>
    class Success<E : Any>(val data: E) : RequestResult<E>
    class Error(val error: Throwable? = null) : RequestResult<Nothing>
}
