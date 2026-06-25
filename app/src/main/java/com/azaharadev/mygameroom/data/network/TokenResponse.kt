package com.azaharadev.mygameroom.data.network

data class TokenResponse(
    val access_token: String,
    val expires_in: Long,
    val token_type: String
)

