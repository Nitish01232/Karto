package com.nitish.android.karto.data.remote

import com.nitish.android.karto.data.remote.dto.NetworkLoginRequest
import com.nitish.android.karto.data.remote.dto.NetworkLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body request: NetworkLoginRequest
    ): NetworkLoginResponse
}