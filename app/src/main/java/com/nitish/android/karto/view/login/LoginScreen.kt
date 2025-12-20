package com.nitish.android.karto.view.login

import androidx.compose.foundation.layout.Arrangement
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
import com.nitish.android.karto.domain.login.UserCredentials

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    navigateToProductScreen: () -> Unit
) {

    val loginUiModel by loginViewModel.uiState.collectAsState()

    // Effect
    LaunchedEffect(Unit) {
        loginViewModel.effect.collect { effect ->
            when (effect) {
                is LoginViewModel.LoginEffect.OnLoginSuccess -> navigateToProductScreen()
            }
        }
    }

    var email by rememberSaveable { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email)) },
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
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
                val userCredentials = UserCredentials(email, password)
                loginViewModel.login(userCredentials)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.login))
        }

        Spacer(modifier = Modifier.height(16.dp))

        val info = if (loginUiModel.isLoading) stringResource(R.string.loading)
        else if (loginUiModel.isSuccess == true) stringResource(R.string.success_message)
        else if (loginUiModel.isSuccess == false) loginUiModel.errorMessage.orEmpty()
        else ""
        Text(info)

    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navigateToProductScreen = {})
}
