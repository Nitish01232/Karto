package com.nitish.android.karto.data.login

import com.nitish.android.karto.data.remote.AuthApi
import com.nitish.android.karto.data.remote.RetrofitClient
import com.nitish.android.karto.data.remote.dto.NetworkLoginRequest
import com.nitish.android.karto.data.remote.dto.NetworkLoginResponse

class LoginDataSource() {
    private val api: AuthApi by lazy {
        RetrofitClient.retrofit.create(AuthApi::class.java)
    }

    suspend fun login(request: NetworkLoginRequest): NetworkLoginResponse =
        api.login(request)
}