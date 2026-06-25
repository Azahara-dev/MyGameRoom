package com.azaharadev.mygameroom.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val twitchAuthApi: TwitchAuthApi = Retrofit.Builder()
        .baseUrl("https://id.twitch.tv/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TwitchAuthApi::class.java)

    val igdbApi: IgdbApi = Retrofit.Builder()
        .baseUrl("https://api.igdb.com/v4/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IgdbApi::class.java)
}