package com.nitish.android.karto.domain.login.model

data class LoginUiModel(
    val isLoading: Boolean = false,
    val isSuccess: Boolean? = null,
    val errorMessage: String? = null,
)