package com.nitish.android.karto.view.product_list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nitish.android.karto.R
import com.nitish.android.karto.domain.product_list.Product
import com.nitish.android.karto.view.product_list.components.ProductItem

@Composable
fun ProductListRoute(
    onProductClick: (Product) -> Unit
) {
    var products by remember {
        mutableStateOf(
            listOf(
                Product(1, "Apple", 1.99, R.drawable.ic_launcher_background),
                Product(2, "Banana", 0.99, R.drawable.ic_launcher_background),
                Product(3, "Orange", 2.49, R.drawable.ic_launcher_background),
                Product(4, "Grapes", 3.99, R.drawable.ic_launcher_background),
                Product(5, "Strawberry", 4.99, R.drawable.ic_launcher_background)
            )
        )
    }

    ProductListScreen(
        products = products,
        onProductClick = onProductClick
    )
}

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        items(products) { product ->
            ProductItem(
                product = product,
                onProductClick = onProductClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview() {
    val products = listOf(
        Product(1, "Apple", 1.99, R.drawable.ic_launcher_background),
        Product(2, "Banana", 0.99, R.drawable.ic_launcher_background),
        Product(3, "Orange", 2.49, R.drawable.ic_launcher_background)
    )
    ProductListScreen(
        products = products,
        onProductClick = {}
    )
}