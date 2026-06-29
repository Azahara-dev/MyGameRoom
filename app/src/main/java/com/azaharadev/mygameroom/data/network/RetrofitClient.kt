package com.azaharadev.mygameroom.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    val twitchAuthApi: TwitchAuthApi = Retrofit.Builder()
        .baseUrl("https://id.twitch.tv/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TwitchAuthApi::class.java)

    val igdbApi: IgdbApi = Retrofit.Builder()
        .baseUrl("https://api.igdb.com/v4/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IgdbApi::class.java)
}