
package com.example.productcatalogue.data.model

// Product rating received from backend
data class Rating(
    val rate: Double,
    val count: Int
)

// Product model matching backend JSON
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)
