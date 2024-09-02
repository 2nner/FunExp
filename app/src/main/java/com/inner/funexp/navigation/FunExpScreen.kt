package com.inner.funexp.navigation

import kotlinx.serialization.Serializable

sealed interface FunExpScreen {

    @Serializable
    data object Home : FunExpScreen

    @Serializable
    data object PdfViewer : FunExpScreen
}