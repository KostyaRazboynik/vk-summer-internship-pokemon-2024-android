package com.kostyarazboynik.utils.extensions

import java.util.Locale

fun String.extractId() = this.substringAfter("pokemon").replace("/", "").toInt()

fun String.getPicUrl(): String {
    val id = this.extractId()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
}

fun String.capitalizeEx(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) {
            it.titlecase(Locale.getDefault())
        }
        else {
            it.toString()
        }
    }
}
