
package com.example.productcatalogue.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.presentation.home.HomeScreen
import com.example.productcatalogue.presentation.home.HomeViewModel
import com.example.productcatalogue.presentation.home.HomeViewModelFactory
import com.example.productcatalogue.presentation.detail.ProductDetailScreen
import com.example.productcatalogue.presentation.detail.ProductDetailViewModel
import com.example.productcatalogue.presentation.detail.ProductDetailViewModelFactory

@Composable
fun AppNavigation(repository: ProductRepository) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        // Home screen route.
        composable("home") {

            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(repository)
            )

            HomeScreen(
                viewModel = homeViewModel,
                onProductClick = { productId ->
                    navController.navigate("product_detail/$productId")
                }
            )
        }

        // Product detail route.
        composable(
            route = "product_detail/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val productId =
                backStackEntry.arguments?.getInt("productId") ?: return@composable

            val detailViewModel: ProductDetailViewModel = viewModel(
                factory = ProductDetailViewModelFactory(repository)
            )

            ProductDetailScreen(
                productId = productId,
                viewModel = detailViewModel,
                onEnquireClick = {
                    // Enquiry navigation will be added in a later step.
                }
            )
        }
    }
}
