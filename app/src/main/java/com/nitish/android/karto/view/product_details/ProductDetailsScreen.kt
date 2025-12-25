package com.nitish.android.karto.view.product_details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.nitish.android.karto.R
import com.nitish.android.karto.domain.products.model.Product
import com.nitish.android.karto.ui.theme.KartoTheme
import com.nitish.android.karto.ui.theme.Orange
import com.nitish.android.karto.view.common.RatingStars

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsRoute(
    onBackClick: () -> Unit,
    viewModel: ProductDetailsViewModel = viewModel(),
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPaddings)
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.product == null) {
                Text(
                    text = uiState.error
                        ?: stringResource(R.string.no_product_details_available),
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                ProductDetailsScreen(
                    product = uiState.product!!,
                )
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    product: Product,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
            )
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .background(
                        color = Color.Red.copy(alpha = 0.4F),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = "-${product.discountPercentage}%",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        // Main Product Image

        Spacer(modifier = Modifier.height(16.dp))

        // Product Images Gallery
        if (product.images.isNotEmpty()) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(product.images) { imageUrl ->
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                Color.White
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Title and Price
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "$${String.format("%.2f", product.discountPrice)}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )


                Text(
                    text = "$${String.format("%.2f", product.price)}",
                    color = Color.Gray,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Rating
        Row(verticalAlignment = Alignment.CenterVertically) {
            RatingStars(rating = product.rating)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "(${product.rating})", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Description
        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Brand and Stock
        Text(
            text = "Brand: ${product.brand}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "In Stock: ${product.stock}",
            style = MaterialTheme.typography.bodyMedium,
            color = remember(product.stock) {
                when {
                    product.stock in 21..29 -> Orange
                    product.stock > 10 -> Color.Green.copy(alpha = 0.8f)
                    else -> Color.Red.copy(alpha = 0.8f)
                }
            })
        Spacer(modifier = Modifier.height(24.dp))

        // Add to Cart Button
        Button(
            onClick = { /* TODO: Add to Cart */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Add to Cart", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailsScreenPreview() {
    KartoTheme {
        ProductDetailsScreen(
            product = Product(
                id = 1,
                title = "Essence Mascara Lash Princess",
                description = "The Essence Mascara Lash Princess is a popular and affordable mascara known for its volumizing and lengthening effects. It features a conical fiber brush that helps to separate and coat each lash, creating a dramatic false lash look.",
                category = "beauty",
                price = 9.99F,
                discountPercentage = 10.36F,
                rating = 4.98f,
                stock = 22,
                brand = "Essence",
                thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
                images = listOf(
                    "https://cdn.dummyjson.com/product-images/1/1.jpg",
                    "https://cdn.dummyjson.com/product-images/1/1.jpg",
                    "https://cdn.dummyjson.com/product-images/1/1.jpg",
                    "https://cdn.dummyjson.com/product-images/1/1.jpg",
                ),
                tags = listOf(),
                sku = "",
                reviews = listOf(),
                returnPolicy = "",
                minimumOrderQuantity = 0,
                discountPrice = 1.0F,
            )
        )
    }
}