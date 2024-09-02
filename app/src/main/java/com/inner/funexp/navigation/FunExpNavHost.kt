package com.inner.funexp.navigation

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inner.funexp.ui.home.FunExpHomeScreen
import com.inner.funexp.ui.pdfviewer.PdFViewerScreen
import kotlin.reflect.typeOf

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun FunExpNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = FunExpScreen.Home,
    ) {
        composable<FunExpScreen.Home> { navBackStackEntry ->
            FunExpHomeScreen(
                onNavigateToPdfViewer = { pdfUri ->
                    navController.currentBackStackEntry?.savedStateHandle?.apply {
                        set("uri", pdfUri.toString())
                    }
                    navController.navigate(
                        route = FunExpScreen.PdfViewer,
                    )
                }
            )
        }

        composable<FunExpScreen.PdfViewer>(
            typeMap = mapOf(typeOf<String>() to NavType.StringType)
        ) { navBackStackEntry ->
            PdFViewerScreen(
                pdfUri = navController.previousBackStackEntry?.savedStateHandle?.get<String>("uri")?.toUri()
                    ?: Uri.EMPTY,
            )
        }
    }
}