package com.nitish.android.karto.domain.login

import com.nitish.android.karto.data.login.LoginRepoImpl
import com.nitish.android.karto.data.login.LoginUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase {

    fun login(userCredentials: UserCredentials): Flow<LoginUiModel> = flow {
        emit(LoginUiModel(isLoading = true))
        delay(1000) // delay given for api repo simulation
        when {
            userCredentials.email.isEmpty() -> {
                emit(
                    LoginUiModel(
                        isLoading = false,
                        isSuccess = false,
                        errorMessage = "Please enter Email Id",
                    )
                )
            }

            userCredentials.password.isEmpty() -> {
                emit(
                    LoginUiModel(
                        isLoading = false,
                        isSuccess = false,
                        errorMessage = "Please enter Password",
                    )
                )
            }

            else -> {
                val loginRepo = LoginRepoImpl()
                val loginResponse = loginRepo.login(userCredentials = userCredentials)
                emit(
                    LoginUiModel(
                        isLoading = false,
                        isSuccess = loginResponse.isSuccess,
                        errorMessage = loginResponse.errorMessage,
                    )
                )
            }
        }

    }
}