package com.nitish.android.karto.navigation

object Routes {
    const val LOGIN = "login"
    const val PRODUCT_LIST = "productList"
    const val PRODUCT_DETAILS = "productDetails/{productId}"

    const val SPLASH = "splash"
    fun productDetails(productId: Int) = "productDetails/$productId"
}