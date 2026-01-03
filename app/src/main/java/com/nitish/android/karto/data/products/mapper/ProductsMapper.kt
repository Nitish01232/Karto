package com.nitish.android.karto.data.products.mapper

import com.nitish.android.karto.common.DATE_FORMAT_DD_MM_YYYY
import com.nitish.android.karto.common.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS_Z
import com.nitish.android.karto.common.formatDate
import com.nitish.android.karto.data.products.dto.NetworkProduct
import com.nitish.android.karto.data.products.dto.NetworkReview
import com.nitish.android.karto.domain.products.model.Product
import com.nitish.android.karto.domain.products.model.Review
import kotlin.math.roundToInt

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
    discountPrice = calculateDiscountedPrice(price ?: 0.0F, discountPercentage ?: 0.0F),
)

fun NetworkReview.toReview() = Review(
    rating = rating ?: 0,
    comment = comment.orEmpty(),
    date = date.orEmpty(),
    reviewerName = reviewerName.orEmpty(),
    reviewerEmail = reviewerEmail.orEmpty(),
    dateForUi = formatDate(
        inputFormat = DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS_Z,
        outputFormat = DATE_FORMAT_DD_MM_YYYY,
        dateString = date.orEmpty(),
    ),
)

fun calculateDiscountedPrice(
    price: Float,
    discountPercentage: Float
): Float {
    if (price <= 0f) return 0f
    if (discountPercentage <= 0f) return price

    return ((price - (price * discountPercentage / 100f)) * 100)
        .roundToInt() / 100f
}