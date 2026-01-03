package com.nitish.android.karto.view.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nitish.android.karto.common.Result
import com.nitish.android.karto.domain.products.GetProductListUseCase
import com.nitish.android.karto.domain.products.model.ProductListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductListViewModel : ViewModel() {

    private val getProductListUseCase: GetProductListUseCase = GetProductListUseCase()
    private val _state = MutableStateFlow(ProductListState())
    val state: StateFlow<ProductListState> = _state.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            getProductListUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            products = result.data,
                            isLoading = false,
                        )
                    }

                    is Result.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.message,
                        )
                    }

                    is Result.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true,
                        )
                    }
                }
            }
        }
    }
}
