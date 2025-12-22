package com.nitish.android.karto.domain.login

data class UserCredentials(
    val email: String,
    val password: String,
)

data class LoginResponse(
    val isSuccess: Boolean,
    val errorMessage: String? = null,
)
