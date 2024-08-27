package com.inner.funexp.navigation

sealed class FunExpScreen(
    val name: String,
) {
    data object Home : FunExpScreen(name = "Home")
}