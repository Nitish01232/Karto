package com.nitish.android.karto.view.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun RatingStars(
    rating: Float,
    maxStars: Int = 5
) {
    Row {
        repeat(maxStars) { index ->
            val icon = when {
                index < rating.toInt() -> Icons.Filled.Star
                index < rating -> Icons.AutoMirrored.Filled.StarHalf
                else -> Icons.Filled.StarBorder
            }

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
@Preview
private fun RatingStarsEmptyPreview() {
    RatingStars(
        rating = 0F,
    )
}

@Composable
@Preview
private fun RatingStarsHalfPreview() {
    RatingStars(
        rating = 2.3F,
    )
}

@Composable
@Preview
private fun RatingStarsFullPreview() {
    RatingStars(
        rating = 5.0F,
    )
}