package com.nitish.android.karto.domain.products.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Float,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Int,
    val tags: List<String>?,
    val brand: String,
    val sku: String,
    val reviews: List<Review>,
    val returnPolicy: String,
    val minimumOrderQuantity: Int,
    val thumbnail: String,
    val images: List<String>,
    val discountPrice: Float,
)

data class Review(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewName: String,
    val reviewerEmail: String,
)
