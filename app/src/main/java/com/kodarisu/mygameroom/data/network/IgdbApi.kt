package com.kodarisu.mygameroom.data.network

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface IgdbApi {
        @POST("games")
        suspend fun getGames(
            @Header("Client-ID") clientId: String,
            @Header("Authorization") authorization: String,
            @Body query: RequestBody
        ): List<GameDto>
}