package com.nitish.android.karto.data.login

import com.google.gson.Gson
import com.nitish.android.karto.common.Result
import com.nitish.android.karto.data.login.mapper.toUserDetails
import com.nitish.android.karto.data.remote.dto.NetworkErrorResponse
import com.nitish.android.karto.data.remote.dto.NetworkLoginRequest
import com.nitish.android.karto.domain.login.LoginRepository
import com.nitish.android.karto.domain.login.model.UserDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class LoginRepositoryImpl() : LoginRepository {

    val dataSource: LoginDataSource = LoginDataSource()

    override fun login(
        username: String,
        password: String
    ): Flow<Result<UserDetails>> = flow {
        try {
            emit(Result.Loading)
            val response = dataSource.login(
                NetworkLoginRequest(username, password)
            )
            emit(Result.Success(response.toUserDetails()))
        } catch (e: HttpException) {

            // 🔹 HTTP errors (400, 401, 500...)
            val message = e.response()
                ?.errorBody()
                ?.charStream()
                ?.let {
                    Gson().fromJson(it, NetworkErrorResponse::class.java).message
                } ?: "Something went wrong"

            emit(Result.Error(message = message, throwable = e))

        } catch (e: Exception) {
            emit(
                Result.Error(
                    message = e.message ?: "Login failed",
                    throwable = e
                )
            )
        }
    }
}
