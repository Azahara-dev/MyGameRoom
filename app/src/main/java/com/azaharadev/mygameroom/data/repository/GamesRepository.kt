package com.azaharadev.mygameroom.data.repository

import com.azaharadev.mygameroom.BuildConfig
import com.azaharadev.mygameroom.data.model.Game
import com.azaharadev.mygameroom.data.model.Genre
import com.azaharadev.mygameroom.data.model.Platform
import com.azaharadev.mygameroom.data.network.GameDto
import com.azaharadev.mygameroom.data.network.RetrofitClient
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class GamesRepository {

    private suspend fun getAccessToken(): String {
        val response = RetrofitClient.twitchAuthApi.getAccessToken(
            clientId = BuildConfig.IGDB_CLIENT_ID,
            clientSecret = BuildConfig.IGDB_CLIENT_SECRET
        )
        return response.access_token
    }

    suspend fun fetchGames(): List<Game> {
        val token = getAccessToken()

        val queryText = "fields id,name,rating,genres.name,platforms.name,cover.url,involved_companies.company.name; where rating != null & cover != null; sort rating desc; limit 50;"
        val requestBody = queryText.toRequestBody("text/plain".toMediaType())

        val gameDtos = RetrofitClient.igdbApi.getGames(
            clientId = BuildConfig.IGDB_CLIENT_ID,
            authorization = "Bearer $token",
            query = requestBody
        )

        return gameDtos.map { it.toGame() }
    }

    private fun mapToPlatform(name: String): Platform? {
        return when {
            name.contains("PC") -> Platform.PC
            name.contains("PlayStation 5") -> Platform.PS5
            name.contains("Xbox") -> Platform.XBOX
            name.contains("Switch") -> Platform.SWITCH2
            else -> null
        }
    }

    private fun mapToGenre(name: String): Genre? {
        return when (name) {
            "Point-and-click" -> Genre.POINT_AND_CLICK
            "Fighting" -> Genre.FIGHTING
            "Shooter" -> Genre.SHOOTER
            "Music" -> Genre.MUSIC
            "Platform" -> Genre.PLATFORM
            "Puzzle" -> Genre.PUZZLE
            "Racing" -> Genre.RACING
            "Real Time Strategy (RTS)" -> Genre.RTS
            "Role-playing (RPG)" -> Genre.RPG
            "Simulator" -> Genre.SIMULATOR
            "Sport" -> Genre.SPORT
            "Strategy" -> Genre.STRATEGY
            "Turn-based strategy (TBS)" -> Genre.TBS
            "Tactical" -> Genre.TACTICAL
            "Hack and slash/Beat 'em up" -> Genre.HACK_AND_SLASH
            "Quiz/Trivia" -> Genre.QUIZ_TRIVIA
            "Pinball" -> Genre.PINBALL
            "Adventure" -> Genre.ADVENTURE
            "Indie" -> Genre.INDIE
            "Arcade" -> Genre.ARCADE
            "Visual Novel" -> Genre.VISUAL_NOVEL
            "Card & Board Game" -> Genre.CARD_AND_BOARD_GAME
            "MOBA" -> Genre.MOBA
            else -> null
        }
    }

    private fun GameDto.toGame(): Game {
        return Game(
            id = this.id,
            title = this.name,
            image = this.cover?.url
                ?.replace("t_thumb", "t_cover_big")
                ?.let { "https:$it" }
                ?: "",
            platforms = this.platforms
                ?.mapNotNull { mapToPlatform(it.name) }
                ?: emptyList(),
            genres = this.genres
                ?.mapNotNull { mapToGenre(it.name) }
                ?: emptyList(),
            developer = this.involved_companies
                ?.firstOrNull()
                ?.company
                ?.name
                ?: "Desconocido",
            mark = (this.rating ?: 0.0) / 20.0,
            isFavourite = false,
            isTendency = false
        )
    }
}