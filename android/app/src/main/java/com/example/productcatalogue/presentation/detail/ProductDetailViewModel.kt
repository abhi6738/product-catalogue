
package com.example.productcatalogue.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProductDetailUiState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String? = null
)

class ProductDetailViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailUiState())

    val uiState: StateFlow<ProductDetailUiState> =
        _uiState.asStateFlow()

    fun loadProduct(productId: Int) {
        viewModelScope.launch {
            _uiState.value = ProductDetailUiState(isLoading = true)

            try {
                val product = repository.getProductById(productId)

                _uiState.value = ProductDetailUiState(
                    isLoading = false,
                    product = product
                )
            } catch (exception: Exception) {
                _uiState.value = ProductDetailUiState(
                    isLoading = false,
                    error = exception.message ?: "Unable to load product"
                )
            }
        }
    }
}
