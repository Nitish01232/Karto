package com.nitish.android.karto.data.products.mapper

import com.nitish.android.karto.data.products.dto.NetworkProduct
import com.nitish.android.karto.data.products.dto.NetworkReview
import com.nitish.android.karto.domain.products.model.Product
import com.nitish.android.karto.domain.products.model.Review

fun NetworkProduct.toProduct() = Product(
    id = id ?: 0,
    title = title.orEmpty(),
    description = description.orEmpty(),
    category = category.orEmpty(),
    price = price ?: 0.0F,
    discountPercentage = discountPercentage ?: 0.0F,
    rating = rating ?: 0.0F,
    stock = stock ?: 0,
    tags = tags.orEmpty(),
    brand = brand.orEmpty(),
    sku = sku.orEmpty(),
    reviews = reviews?.map { it.toReview() }.orEmpty(),
    returnPolicy = returnPolicy.orEmpty(),
    minimumOrderQuantity = minimumOrderQuantity ?: 0,
    thumbnail = thumbnail.orEmpty(),
    images = images.orEmpty(),
)

fun NetworkReview.toReview() = Review(
    rating = rating ?: 0,
    comment = comment.orEmpty(),
    date = date.orEmpty(),
    reviewName = reviewName.orEmpty(),
    reviewerEmail = reviewerEmail.orEmpty(),
)