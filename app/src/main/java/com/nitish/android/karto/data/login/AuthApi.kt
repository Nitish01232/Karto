package com.nitish.android.karto.data.login

import com.nitish.android.karto.data.login.dto.NetworkLoginRequest
import com.nitish.android.karto.data.login.dto.NetworkLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body request: NetworkLoginRequest
    ): NetworkLoginResponse
}