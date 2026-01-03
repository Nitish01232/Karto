package com.nitish.android.karto.domain.products

import com.nitish.android.karto.common.Result
import com.nitish.android.karto.domain.products.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun getProducts(): Flow<Result<List<Product>>>

    fun getProductDetails(productId: Int): Flow<Result<Product>>

}