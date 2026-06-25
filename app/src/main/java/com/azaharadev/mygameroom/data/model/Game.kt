package com.azaharadev.mygameroom.data.model

enum class Platform {
    PC, PS5, XBOX, SWITCH2
}

enum class Genre {
    POINT_AND_CLICK,
    FIGHTING,
    SHOOTER,
    MUSIC,
    PLATFORM,
    PUZZLE,
    RACING,
    RTS,
    RPG,
    SIMULATOR,
    SPORT,
    STRATEGY,
    TBS,
    TACTICAL,
    HACK_AND_SLASH,
    QUIZ_TRIVIA,
    PINBALL,
    ADVENTURE,
    INDIE,
    ARCADE,
    VISUAL_NOVEL,
    CARD_AND_BOARD_GAME,
    MOBA
}

data class Game(
    val id: Int,
    val image: String,
    val platforms: List<Platform>,
    val title: String,
    val developer: String,
    val mark: Double,
    val isFavourite: Boolean,
    val isTendency: Boolean,
    val genres: List<Genre>
)