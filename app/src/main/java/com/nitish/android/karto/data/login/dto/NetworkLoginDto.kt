package com.nitish.android.karto.data.login.dto

data class NetworkLoginRequest(
    val username: String,
    val password: String,
)

data class NetworkLoginResponse(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val accessToken: String,
    val refreshToken: String,
    val message: String,
)

data class NetworkErrorResponse(
    val message: String
)