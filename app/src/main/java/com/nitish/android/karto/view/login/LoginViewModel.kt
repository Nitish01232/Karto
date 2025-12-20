package com.nitish.android.karto.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nitish.android.karto.data.login.LoginUiModel
import com.nitish.android.karto.domain.login.LoginUseCase
import com.nitish.android.karto.domain.login.UserCredentials
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val loginUseCase: LoginUseCase = LoginUseCase()

    private val _uiState = MutableStateFlow(LoginUiModel())
    val uiState: StateFlow<LoginUiModel> = _uiState

    private val _effect = MutableSharedFlow<LoginEffect>()
    val effect = _effect.asSharedFlow()

    sealed class LoginEffect {
        class OnLoginSuccess : LoginEffect()
    }

    fun login(userCredentials: UserCredentials) {
        viewModelScope.launch {
            loginUseCase.login(
                userCredentials
            ).collect { loginUiModel ->
                _uiState.update { loginUiModel }
                if (loginUiModel.isSuccess == true) {
                    _effect.emit(LoginEffect.OnLoginSuccess())
                }
            }
        }
    }

}