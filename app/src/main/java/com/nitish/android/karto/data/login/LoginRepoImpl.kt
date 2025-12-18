package com.nitish.android.karto.data.login

import com.nitish.android.karto.common.LOGIN_CREDENTIALS_EMPTY_ERROR
import com.nitish.android.karto.domain.login.LoginRepo
import com.nitish.android.karto.domain.login.LoginResponse
import com.nitish.android.karto.domain.login.UserCredentials
import kotlinx.coroutines.delay

class LoginRepoImpl : LoginRepo {

    override suspend fun login(userCredentials: UserCredentials): LoginResponse {
        // Simulate a network call
        delay(1500)

        return if (userCredentials.email == "nitish@gmail.com"
            && userCredentials.password == "1234"
        ) {
            LoginResponse(
                isSuccess = true,
            )
        } else LoginResponse(
            isSuccess = false,
            errorMessage = LOGIN_CREDENTIALS_EMPTY_ERROR,
        )
    }

}