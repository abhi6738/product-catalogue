
package com.example.productcatalogue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productcatalogue.data.remote.RetrofitClient
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.presentation.home.HomeScreen
import com.example.productcatalogue.presentation.home.HomeViewModel
import com.example.productcatalogue.presentation.home.HomeViewModelFactory
import com.example.productcatalogue.ui.theme.ProductCatalogueTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create repository using the Retrofit API service.
        val repository = ProductRepository(RetrofitClient.apiService)

        setContent {
            ProductCatalogueTheme {
                // Create HomeViewModel with our custom factory.
                val homeViewModel: HomeViewModel = viewModel(
                    factory = HomeViewModelFactory(repository)
                )

                HomeScreen(viewModel = homeViewModel)
            }
        }
    }
}
