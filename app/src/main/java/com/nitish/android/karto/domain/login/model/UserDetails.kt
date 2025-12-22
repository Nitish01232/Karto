package com.nitish.android.karto.domain.login.model

data class UserDetails(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val image: String,
    val accessToken: String,
    val refreshToken: String
)