package com.inner.funexp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.inner.funexp.navigation.FunExpNavHost

@Composable
fun FunExpMain() {
    val navController = rememberNavController()

    FunExpNavHost(
        navController = navController,
    )
}