package com.azaharadev.mygameroom.data.model

enum class Platform {
    PC, PS5, XBOX, SWITCH2
}

data class Game(
    val image: String,
    val platforms: List<Platform>,
    val title: String,
    val developer: String,
    val mark: Double,
    val isFavourite: Boolean,
    val isTendency: Boolean
)