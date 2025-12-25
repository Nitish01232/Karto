package com.nitish.android.karto.view.product_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nitish.android.karto.common.Result
import com.nitish.android.karto.domain.products.GetProductDetailsUseCase
import com.nitish.android.karto.domain.products.model.Product
import com.nitish.android.karto.domain.products.model.ProductState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val productId: Int =
        savedStateHandle["productId"]
            ?: error("productId missing")

    private val useCase: GetProductDetailsUseCase = GetProductDetailsUseCase()

    private val _state: MutableStateFlow<ProductState> = MutableStateFlow(ProductState())
    val state = _state.asStateFlow()

    init {
        getProductDetails(productId)
    }

    private var job: Job? = null
    private fun getProductDetails(productId: Int) {
        job?.cancel()
        job = viewModelScope.launch {
            useCase.invoke(productId).collect { result ->
                when (result) {
                    is Result.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.message,
                        )
                    }

                    Result.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true,
                        )
                    }

                    is Result.Success<*> -> {
                        _state.value = _state.value.copy(
                            product = result.data as Product?,
                            isLoading = false,
                        )
                    }
                }

            }
        }
    }
}