package com.nitish.android.karto.data.products.dto

data class NetworkProductsResponse(
    val products: List<NetworkProduct>? = null
)

data class NetworkProduct(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val category: String? = null,
    val price: Float? = null,
    val discountPercentage: Float? = null,
    val rating: Float? = null,
    val stock: Int? = null,
    val tags: List<String>? = null,
    val brand: String? = null,
    val sku: String? = null,
    val reviews: List<NetworkReview>? = null,
    val returnPolicy: String? = null,
    val minimumOrderQuantity: Int? = null,
    val thumbnail: String? = null,
    val images: List<String>? = null,
)

data class NetworkReview(
    val rating: Int? = null,
    val comment: String? = null,
    val date: String? = null,
    val reviewName: String? = null,
    val reviewerEmail: String? = null,
)
