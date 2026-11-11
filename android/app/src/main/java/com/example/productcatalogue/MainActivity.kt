
package com.example.productcatalogue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.productcatalogue.data.remote.RetrofitClient
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.navigation.AppNavigation
import com.example.productcatalogue.ui.theme.ProductCatalogueTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create repository using Retrofit API service.
        val repository = ProductRepository(RetrofitClient.apiService)

        setContent {
            ProductCatalogueTheme {

                // Navigation is managed by AppNavigation.
                AppNavigation(repository = repository)

            }
        }
    }
}
