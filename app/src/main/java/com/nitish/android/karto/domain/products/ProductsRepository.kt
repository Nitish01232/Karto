package com.nitish.android.karto.domain.products

import com.nitish.android.karto.domain.products.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun getProducts(): Flow<com.nitish.android.karto.common.Result<List<Product>>>

}