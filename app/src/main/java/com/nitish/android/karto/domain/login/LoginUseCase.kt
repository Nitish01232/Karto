package com.nitish.android.karto.domain.login

import com.nitish.android.karto.common.Result
import com.nitish.android.karto.data.login.LoginRepositoryImpl
import com.nitish.android.karto.domain.login.model.UserDetails
import kotlinx.coroutines.flow.Flow

class LoginUseCase() {

    var repository: LoginRepository = LoginRepositoryImpl()

    operator fun invoke(
        username: String,
        password: String
    ): Flow<Result<UserDetails>> {
        return repository.login(username, password)
    }
}