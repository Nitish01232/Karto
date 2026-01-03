package com.nitish.android.karto.view.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nitish.android.karto.R

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = viewModel(),
    navigateToNextScreen: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.navigateToNextScreen.collect {
            navigateToNextScreen()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.splash_screen_image),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Text(
            text = "KARTO",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen {}
}

