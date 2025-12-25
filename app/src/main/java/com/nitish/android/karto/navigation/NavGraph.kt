package com.nitish.android.karto.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nitish.android.karto.view.login.LoginRoute
import com.nitish.android.karto.view.product_details.ProductDetailsRoute
import com.nitish.android.karto.view.product_details.ProductDetailsViewModel
import com.nitish.android.karto.view.product_list.ProductListRoute

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            LoginRoute(navigateToProductScreen = {
                navController.navigate(Routes.PRODUCT_LIST)
            })
        }
        composable(Routes.PRODUCT_LIST) {
            ProductListRoute(
                onProductClick = { productId ->
                    navController.navigate(Routes.productDetails(productId))
                }
            )
        }
        composable(
            route = Routes.PRODUCT_DETAILS,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) {
            val viewModel: ProductDetailsViewModel = viewModel()
            ProductDetailsRoute(
                onBackClick = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
    }
}