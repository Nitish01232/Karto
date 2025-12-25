package com.nitish.android.karto.domain.products.model

data class ProductListState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class ProductState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)