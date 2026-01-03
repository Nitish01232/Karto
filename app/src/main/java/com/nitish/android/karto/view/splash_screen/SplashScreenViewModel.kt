package com.nitish.android.karto.view.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SplashScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _navigateToNextScreen = MutableSharedFlow<Unit>()
    val navigateToNextScreen = _navigateToNextScreen.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _navigateToNextScreen.emit(Unit)
        }
    }
}