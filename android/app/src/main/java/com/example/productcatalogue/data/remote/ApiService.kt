
package com.example.productcatalogue.data.remote

import com.example.productcatalogue.data.model.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.POST

// Request body for submitting customer enquiries
data class EnquiryRequest(
    val productId: Int,
    val customerName: String,
    val email: String,
    val message: String
)

// Response returned by backend
data class EnquiryResponse(
    val success: Boolean,
    val message: String
)

interface ApiService {

    // GET /api/products
    @GET("api/products")
    suspend fun getProducts(): List<Product>

    // GET /api/products/featured
    @GET("api/products/featured")
    suspend fun getFeaturedProducts(): List<Product>

    // GET /api/products/categories
    @GET("api/products/categories")
    suspend fun getCategories(): List<String>

    // GET /api/products/category/electronics
    @GET("api/products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String
    ): List<Product>

    // GET /api/products?search=shirt
    @GET("api/products")
    suspend fun searchProducts(
        @Query("search") query: String
    ): List<Product>

    // GET /api/products/1
    @GET("api/products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): Product

    // POST /api/enquiries
    @POST("api/enquiries")
    suspend fun submitEnquiry(
        @Body request: EnquiryRequest
    ): EnquiryResponse
}
