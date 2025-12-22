package com.nitish.android.karto.view.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nitish.android.karto.R

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel = viewModel(),
    navigateToProductScreen: () -> Unit
) {
    val loginUiModel by loginViewModel.state.collectAsState()

    // Effect
    LaunchedEffect(Unit) {
        loginViewModel.effect.collect { effect ->
            when (effect) {
                is LoginViewModel.LoginEffect.OnLoginSuccess -> navigateToProductScreen()
            }
        }
    }

    var emailId by rememberSaveable { mutableStateOf("nitish@gmail.com") }
    var password by remember { mutableStateOf("1234") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box {
        if (loginUiModel.isLoading) {
            CenteredLoader()
        }

        LoginScreen(
            emailId = emailId,
            password = password,
            passwordVisible = passwordVisible,
            onEmailIdChanged = { email ->
                emailId = email
            },
            onPasswordChanged = { pwd ->
                password = pwd
            },
            togglePasswordVisibility = {
                passwordVisible = passwordVisible.not()
            },
            onLoginButtonClick = {
                loginViewModel.login(
                    email = emailId,
                    password = password,
                )
            },
            info = loginUiModel.errorMessage.orEmpty()
        )
    }
}

@Composable
private fun LoginScreen(
    emailId: String,
    password: String,
    passwordVisible: Boolean,
    info: String,
    onEmailIdChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    togglePasswordVisibility: () -> Unit,
    onLoginButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailId,
            onValueChange = { onEmailIdChanged(it) },
            label = { Text(stringResource(R.string.email)) },
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { onPasswordChanged(it) },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(onClick = { togglePasswordVisibility() }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.VisibilityOff
                        else
                            Icons.Filled.Visibility,
                        contentDescription = ""
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onLoginButtonClick()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.login))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(info)

    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun LoginScreenPasswordLockPreview() {
    LoginScreen(
        emailId = "Blah",
        password = "pass",
        passwordVisible = false,
        info = "",
        onEmailIdChanged = {},
        onPasswordChanged = {},
        togglePasswordVisibility = {},
        onLoginButtonClick = {},
    )
}


@Preview(
    showSystemUi = true
)
@Composable
fun LoginScreenPasswordVisilePreview() {
    LoginScreen(
        emailId = "Blah",
        password = "pass",
        passwordVisible = true,
        info = "",
        onEmailIdChanged = {},
        onPasswordChanged = {},
        togglePasswordVisibility = {},
        onLoginButtonClick = {},
    )
}


@Preview(
    showSystemUi = true
)
@Composable
fun LoginScreenWithInfoPreview() {
    LoginScreen(
        emailId = "Blah",
        password = "pass",
        passwordVisible = true,
        info = "Enter valid email",
        onEmailIdChanged = {},
        onPasswordChanged = {},
        togglePasswordVisibility = {},
        onLoginButtonClick = {},
    )
}
