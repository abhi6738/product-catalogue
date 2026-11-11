
package com.example.productcatalogue.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    private const val BASE_URL = "http://127.0.0.1:3000/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Create the API service implementation.
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
