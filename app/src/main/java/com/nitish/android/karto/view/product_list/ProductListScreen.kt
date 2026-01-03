package com.nitish.android.karto.view.product_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nitish.android.karto.R
import com.nitish.android.karto.domain.products.model.Product
import com.nitish.android.karto.domain.products.model.ProductListState
import com.nitish.android.karto.view.product_list.componants.ProductItem

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductListRoute(
    viewModel: ProductListViewModel = viewModel(),
    onProductClick: (Int) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                title = {
                    Text(
                        "Karto",
                        textAlign = TextAlign.Center,
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.products.isEmpty()) {
                Text(
                    text = uiState.error
                        ?: stringResource(R.string.no_products_available),
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
        modifier = Modifier,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
            discountPercentage = 12.00F,
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
            minimumOrderQuantity = 0,
            discountPrice = 1.0F,
        ),
        Product(
            id = 2,
            title = "iPhone X",
            description = "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
            price = 899.0f,
            discountPercentage = 17.00F,
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
            minimumOrderQuantity = 0,
            discountPrice = 1.0F,
        ),
    )
    ProductListScreen(
        uiState = ProductListState(products = products),
        onProductClick = {}
    )
}