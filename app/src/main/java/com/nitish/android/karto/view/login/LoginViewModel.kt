package com.nitish.android.karto.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nitish.android.karto.common.Result
import com.nitish.android.karto.domain.login.LoginUseCase
import com.nitish.android.karto.domain.login.model.LoginUiModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val loginUseCase: LoginUseCase = LoginUseCase()

    private val _state = MutableStateFlow(LoginUiModel())
    val state: StateFlow<LoginUiModel> = _state

    private val _effect = MutableSharedFlow<LoginEffect>()
    val effect = _effect.asSharedFlow()

    sealed class LoginEffect {
        class OnLoginSuccess : LoginEffect()
    }

    var loginJob: Job? = null
    fun login(
        email: String,
        password: String,
    ) {
        loginJob?.cancel()
        loginJob = viewModelScope.launch {
            loginUseCase.invoke(
                email, password
            ).collect { result ->

                when (result) {
                    is Result.Loading -> {
                        _state.update { state.value.copy(isLoading = true) }
                    }

                    is Result.Success -> {
                        _state.update {
                            state.value.copy(
                                isLoading = false,
                                isSuccess = true,
                            )
                        }
                        _effect.emit(LoginEffect.OnLoginSuccess())
                    }

                    is Result.Error -> {
                        _state.update { value ->
                            value.copy(
                                isLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }
                }
            }
        }
    }

}