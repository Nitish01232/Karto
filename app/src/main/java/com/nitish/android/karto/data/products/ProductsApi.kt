package com.nitish.android.karto.data.products

import com.nitish.android.karto.data.products.dto.NetworkProductsResponse
import retrofit2.http.GET

interface ProductsApi {

    @GET("products")
    suspend fun getProducts(): NetworkProductsResponse

}