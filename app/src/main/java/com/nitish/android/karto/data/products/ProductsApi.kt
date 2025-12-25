package com.nitish.android.karto.data.products

import com.nitish.android.karto.data.products.dto.NetworkProduct
import com.nitish.android.karto.data.products.dto.NetworkProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {

    @GET("products")
    suspend fun getProducts(): NetworkProductsResponse

    @GET("products/{productId}")
    suspend fun getProductDetails(
        @Path("productId") productId: Int,
    ): NetworkProduct
}