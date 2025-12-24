package com.nitish.android.karto.data.network_common

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ErrorLoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (!response.isSuccessful) {
            val body = response.peekBody(Long.MAX_VALUE).string()
            Log.d("API_ERROR", body)
        }

        return response
    }
}