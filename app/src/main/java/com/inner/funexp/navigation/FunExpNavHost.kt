package com.inner.funexp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inner.funexp.ui.home.FunExpHome

@Composable
fun FunExpNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = FunExpScreen.Home.name,
    ) {
        composable(route = FunExpScreen.Home.name) {
            FunExpHome()
        }
    }
}