package com.nitish.android.karto.domain.login

import com.nitish.android.karto.common.Result
import com.nitish.android.karto.domain.login.model.UserDetails
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(
        username: String,
        password: String
    ): Flow<Result<UserDetails>>
}