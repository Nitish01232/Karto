package com.nitish.android.karto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nitish.android.karto.view.login.LoginRoute
import com.nitish.android.karto.view.product_list.ProductListRoute
import com.nitish.android.karto.view.product_list.ProductListScreen

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
            ProductListRoute(onProductClick = {})
        }
    }
}