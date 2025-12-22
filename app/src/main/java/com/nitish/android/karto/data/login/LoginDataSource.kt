package com.nitish.android.karto.data.login

import com.nitish.android.karto.data.network_common.RetrofitClient
import com.nitish.android.karto.data.login.dto.NetworkLoginRequest
import com.nitish.android.karto.data.login.dto.NetworkLoginResponse

class LoginDataSource() {
    private val api: AuthApi by lazy {
        RetrofitClient.retrofit.create(AuthApi::class.java)
    }

    suspend fun login(request: NetworkLoginRequest): NetworkLoginResponse =
        api.login(request)
}