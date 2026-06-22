package com.azaharadev.mygameroom.data.model

object MockData {
    val games: List<Game> = listOf(
        Game(
            image = "https://www.codigi.es/wp-content/uploads/2021/01/Cyberpunk-2077-PC-COVER.jpg",
            platforms = listOf(Platform.PC, Platform.PS5, Platform.XBOX),
            title = "Cyberpunk 2077",
            developer = "CD Projekt Red",
            mark = 4.6,
            isFavourite = false,
            isTendency = true
        ),
        Game(
            image = "https://uvejuegos.com/img/caratulas/62850/1_pc.jpg",
            platforms = listOf(Platform.PC, Platform.PS5, Platform.XBOX),
            title = "Elden Ring",
            developer = "From Software",
            mark = 4.9,
            isFavourite = false,
            isTendency = true
        )
    )
}