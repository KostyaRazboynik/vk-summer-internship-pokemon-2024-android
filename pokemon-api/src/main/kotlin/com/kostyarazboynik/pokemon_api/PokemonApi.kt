package com.kostyarazboynik.pokemon_api

import androidx.annotation.IntRange
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kostyarazboynik.pokemon_api.model.response.PokemonListResponse
import com.kostyarazboynik.pokemon_api.model.response.PokemonResponse
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * [API Documentation](https://pokeapi.co/)
 */
interface PokemonApi {

    @GET("pokemon/")
    suspend fun loadPokemons(
        @Query("limit") @IntRange(from = 1, to = 30) limit: Int = 20,
        @Query("offset") @IntRange(from = 0) offset: Int = 0,
    ): Result<PokemonListResponse>

    @GET("pokemon/{id}/")
    suspend fun loadPokemon(
        @Path("id") @IntRange(from = 1) id: Int,
    ): Result<PokemonResponse>

}

fun PokemonApi(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json { ignoreUnknownKeys = true },
): PokemonApi {
    return retrofit(baseUrl, okHttpClient, json).create()
}

private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient?,
    json: Json,
): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(converterFactory(json))
    .addCallAdapterFactory(ResultCallAdapterFactory.create())
    .client(client(okHttpClient))
    .build()

private fun client(
    okHttpClient: OkHttpClient?,
): OkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder()).build()

private fun converterFactory(
    json: Json
) = json.asConverterFactory("application/json".toMediaType())
