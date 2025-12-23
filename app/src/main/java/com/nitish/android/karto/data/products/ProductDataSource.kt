package com.nitish.android.karto.data.products

import com.nitish.android.karto.data.network_common.RetrofitClient
import com.nitish.android.karto.data.products.dto.NetworkProductsResponse

class ProductDataSource() {
    private val api: ProductsApi by lazy {
        RetrofitClient.retrofit.create(ProductsApi::class.java)
    }

    suspend fun getProducts(): NetworkProductsResponse =
        api.getProducts()

}