package com.nitish.android.karto.domain.products

import com.nitish.android.karto.common.Result
import com.nitish.android.karto.data.products.ProductsRepositoryImpl
import com.nitish.android.karto.domain.products.model.Product
import kotlinx.coroutines.flow.Flow

class GetProductDetailsUseCase(
    private val repository: ProductsRepository = ProductsRepositoryImpl()
) {
    operator fun invoke(productId: Int): Flow<Result<Product>> {
        return repository.getProductDetails(productId)
    }
}