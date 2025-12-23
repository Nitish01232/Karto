package com.nitish.android.karto.view.product_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nitish.android.karto.domain.products.model.Product
import com.nitish.android.karto.view.product_list.componants.ProductItem

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductListRoute(
    onProductClick: (Int) -> Unit,
    viewModel: ProductListViewModel = viewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minWidth = 44.dp),
                        text = "Product Screen"
                    )
                }
            )
        },
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPaddings)
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.products.isEmpty()) {
                Text(
                    text = uiState.error
                        ?: "No Products Available", // TODO add this string in string.xml
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                ProductListScreen(
                    uiState = uiState,
                    onProductClick = onProductClick
                )
            }
        }
    }

}

@Composable
fun ProductListScreen(
    uiState: ProductListState,
    onProductClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(8.dp)
    ) {
        items(uiState.products) { product ->
            ProductItem(
                product = product,
                onProductClick = { onProductClick(product.id) }
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductListScreenContentPreview() {
    val products = listOf(
        Product(
            id = 1,
            title = "iPhone 9",
            description = "An apple mobile which is nothing like apple",
            price = 549.0f,
            discountPercentage = 12.96f,
            rating = 4.69f,
            stock = 94,
            brand = "Apple",
            category = "smartphones",
            thumbnail = "",
            images = listOf(),
            tags = listOf(),
            sku = "",
            reviews = listOf(),
            returnPolicy = "",
            minimumOrderQuantity = 0
        ),
        Product(
            id = 2,
            title = "iPhone X",
            description = "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
            price = 899.0f,
            discountPercentage = 17.94f,
            rating = 4.44f,
            stock = 34,
            brand = "Apple",
            category = "smartphones",
            thumbnail = "...",
            images = listOf(),
            tags = listOf(),
            sku = "",
            reviews = listOf(),
            returnPolicy = "",
            minimumOrderQuantity = 0
        ),
    )
    ProductListScreen(
        uiState = ProductListState(products = products),
        onProductClick = {}
    )
}