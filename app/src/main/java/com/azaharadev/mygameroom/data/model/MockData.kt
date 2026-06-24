package com.azaharadev.mygameroom.data.model

object MockData {
    val games: List<Game> = listOf(
        Game(
            id = 1,
            image = "https://www.codigi.es/wp-content/uploads/2021/01/Cyberpunk-2077-PC-COVER.jpg",
            platforms = listOf(Platform.PC, Platform.PS5, Platform.XBOX),
            title = "Cyberpunk 2077",
            developer = "CD Projekt Red",
            mark = 2.0,
            isFavourite = true,
            isTendency = true,
            genres = listOf(Genre.RPG, Genre.ACCION)
        ),
        Game(
            id = 2,
            image = "https://cdng.europosters.eu/pod_public/1300/216712.jpg",
            platforms = listOf(Platform.PC, Platform.PS5, Platform.XBOX),
            title = "Elden Ring",
            developer = "From Software",
            mark = 4.9,
            isFavourite = false,
            isTendency = true,
            genres = listOf(Genre.RPG, Genre.ACCION)
        ),
        Game(
            id = 3,
            image = "https://static.wikia.nocookie.net/godofwar/images/c/ca/Portada_God_of_War_Ragnarok.png/revision/latest?cb=20211008000423&path-prefix=es",
            platforms = listOf(Platform.PS5),
            title = "God of War Ragnarök",
            developer = "Santa Monica Studio",
            mark = 4.8,
            isFavourite = true,
            isTendency = false,
            genres = listOf(Genre.ACCION)
        ),
        Game(
            id = 4,
            image =
                "https://i.redd.it/which-cover-is-better-v0-9h0sjl9isccb1.jpg?width=624&format=pjpg&auto=webp&s=f855c57dde30976553712c8937292a0f177fd775",
            platforms = listOf(Platform.PC, Platform.PS5),
            title = "Baldur's Gate 3",
            developer = "Larian Studios",
            mark = 4.9,
            isFavourite = false,
            isTendency = false,
            genres = listOf(Genre.RPG, Genre.ESTRATEGIA)
        ),
        Game(
            id = 5,
            image = "https://static1-es.millenium.gg/articles/1/48/93/1/@/266332-fifa23-article_m-1.JPG",
            platforms = listOf(Platform.PC, Platform.PS5, Platform.XBOX),
            title = "FIFA 23",
            developer = "EA Sports",
            mark = 4.0,
            isFavourite = false,
            isTendency = false,
            genres = listOf(Genre.DEPORTES)
        )
    )
}