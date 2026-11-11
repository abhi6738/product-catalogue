
package com.example.productcatalogue.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Represents all possible data shown on the Home screen.
data class HomeUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)

class HomeViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    // Private state that only the ViewModel can modify.
    private val _uiState = MutableStateFlow(HomeUiState())

    // Public read-only state observed by the Compose UI.
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        // Fetch featured products when ViewModel is created.
        loadFeaturedProducts()
    }

    fun loadFeaturedProducts() {
        viewModelScope.launch {
            // Show loading indicator.
            _uiState.value = HomeUiState(isLoading = true)

            try {
                // Make the API call through the repository.
                val products = repository.getFeaturedProducts()

                // Publish the successful response.
                _uiState.value = HomeUiState(
                    isLoading = false,
                    products = products
                )
            } catch (exception: Exception) {
                // Publish an error if the API call fails.
                _uiState.value = HomeUiState(
                    isLoading = false,
                    error = exception.message ?: "Something went wrong"
                )
            }
        }
    }
}
