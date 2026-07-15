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

fun Genre.displayName(): String {
    return when (this) {
        Genre.POINT_AND_CLICK -> "Point & Click"
        Genre.FIGHTING -> "Lucha"
        Genre.SHOOTER -> "Shooter"
        Genre.MUSIC -> "Música"
        Genre.PLATFORM -> "Plataformas"
        Genre.PUZZLE -> "Puzzle"
        Genre.RACING -> "Carreras"
        Genre.RTS -> "RTS"
        Genre.RPG -> "RPG"
        Genre.SIMULATOR -> "Simulador"
        Genre.SPORT -> "Deportes"
        Genre.STRATEGY -> "Estrategia"
        Genre.TBS -> "TBS"
        Genre.TACTICAL -> "Tácticos"
        Genre.HACK_AND_SLASH -> "Hack & Slash"
        Genre.QUIZ_TRIVIA -> "Quiz/Trivial"
        Genre.PINBALL -> "Pinball"
        Genre.ADVENTURE -> "Aventuras"
        Genre.INDIE -> "Indie"
        Genre.ARCADE -> "Arcade"
        Genre.VISUAL_NOVEL -> "Novela visual"
        Genre.CARD_AND_BOARD_GAME -> "Card & Board Game"
        Genre.MOBA -> "MOBA"
    }
}

fun Genre.igdbId(): Int {
    return when (this) {
        Genre.POINT_AND_CLICK -> 2
        Genre.FIGHTING -> 4
        Genre.SHOOTER -> 5
        Genre.MUSIC -> 7
        Genre.PLATFORM -> 8
        Genre.PUZZLE -> 9
        Genre.RACING -> 10
        Genre.RTS -> 11
        Genre.RPG -> 12
        Genre.SIMULATOR -> 13
        Genre.SPORT -> 14
        Genre.STRATEGY -> 15
        Genre.TBS -> 16
        Genre.TACTICAL -> 24
        Genre.HACK_AND_SLASH -> 25
        Genre.QUIZ_TRIVIA -> 26
        Genre.PINBALL -> 30
        Genre.ADVENTURE -> 31
        Genre.INDIE -> 32
        Genre.ARCADE -> 33
        Genre.VISUAL_NOVEL -> 34
        Genre.CARD_AND_BOARD_GAME -> 35
        Genre.MOBA -> 36
    }
}