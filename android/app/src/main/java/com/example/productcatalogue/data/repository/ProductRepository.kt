
package com.example.productcatalogue.data.repository

import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.remote.ApiService

class ProductRepository(
    private val apiService: ApiService
) {

    // Fetch all products
    suspend fun getAllProducts(): List<Product> {
        return apiService.getProducts()
    }

    // Fetch five featured products
    suspend fun getFeaturedProducts(): List<Product> {
        return apiService.getFeaturedProducts()
    }

    // Fetch available categories
    suspend fun getCategories(): List<String> {
        return apiService.getCategories()
    }

    // Fetch products belonging to a category
    suspend fun getProductsByCategory(
        category: String
    ): List<Product> {
        return apiService.getProductsByCategory(category)
    }

    // Search products
    suspend fun searchProducts(
        query: String
    ): List<Product> {
        return apiService.searchProducts(query)
    }

    // Fetch individual product details
    suspend fun getProductById(id: Int): Product {
        return apiService.getProductById(id)
    }
}
