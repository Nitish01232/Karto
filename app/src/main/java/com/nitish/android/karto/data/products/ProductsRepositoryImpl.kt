package com.nitish.android.karto.data.products

import com.google.gson.Gson
import com.nitish.android.karto.common.Result
import com.nitish.android.karto.data.network_common.NetworkErrorResponse
import com.nitish.android.karto.data.products.mapper.toProduct
import com.nitish.android.karto.domain.products.ProductsRepository
import com.nitish.android.karto.domain.products.model.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class ProductsRepositoryImpl() : ProductsRepository {

    val dataSource: ProductDataSource = ProductDataSource()

    override fun getProducts(): Flow<Result<List<Product>>> = flow {
        try {
            emit(Result.Loading)
            delay(1000)
            val response = dataSource.getProducts()
            emit(Result.Success(response.products?.map { it.toProduct() } ?: emptyList()))
        } catch (e: HttpException) {

            val message = e.response()
                ?.errorBody()
                ?.charStream()
                ?.let { reader ->
                    runCatching {
                        Gson().fromJson(reader, NetworkErrorResponse::class.java)
                    }.getOrNull()?.message
                }
                ?: "Something went wrong"

            emit(Result.Error(message = message, throwable = e))

        } catch (e: Exception) {
            emit(
                Result.Error(
                    message = e.message ?: "An unexpected error occurred",
                    throwable = e
                )
            )
        }
    }

    override fun getProductDetails(productId: Int): Flow<Result<Product>> = flow {
        try {
            emit(Result.Loading)
            val result = dataSource.getProductDetails(productId).toProduct()
            emit(Result.Success(result))
        } catch (e: HttpException) {
            val message = e.response()
                ?.errorBody()
                ?.charStream()
                ?.let { reader ->
                    runCatching {
                        Gson().fromJson(reader, NetworkErrorResponse::class.java)
                    }.getOrNull()?.message
                }
                ?: "Something went wrong"

            emit(Result.Error(message = message, throwable = e))
        } catch (e: Exception) {
            emit(
                Result.Error(
                    message = e.message ?: "An unexpected error occurred",
                    throwable = e
                )
            )
        }
    }

}