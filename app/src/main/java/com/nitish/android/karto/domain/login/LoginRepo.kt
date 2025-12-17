package com.nitish.android.karto.domain.login

interface LoginRepo {

    suspend fun login(userCredentials: UserCredentials): LoginResponse
}