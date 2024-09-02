package com.inner.funexp.ui.pdfviewer

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.lifecycle.withStarted
import androidx.pdf.viewer.fragment.PdfViewerFragment
import com.inner.funexp.databinding.FragmentPdfViewerBinding
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun PdFViewerScreen(
    pdfUri: Uri,
) {
    var errorToast by remember { mutableStateOf<String?>(null) }
    val snackBarHostState = remember { SnackbarHostState() }
    val filename: String = remember(key1 = pdfUri) {
        pdfUri.path?.let { File(it).name } ?: ""
    }

    LaunchedEffect(key1 = errorToast) {
        errorToast?.let { errorMessage ->
            snackBarHostState.showSnackbar(errorMessage)
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { _ ->
        FunExpPdfViewer(
            pdfUri = pdfUri,
            fileName = filename,
            showErrorMessage = { message ->
                errorToast = message
            }
        )
    }
}

@Composable
fun FunExpPdfViewer(
    pdfUri: Uri,
    fileName: String,
    showErrorMessage: (String) -> Unit,
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            text = fileName,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
        )
        AndroidViewBinding(
            modifier = Modifier.fillMaxSize(),
            factory = FragmentPdfViewerBinding::inflate,
            update = {
                scope.launch {
                    pdfviewer.getFragment<PdfViewerFragment>().apply {
                        withStarted {
                            try {
                                documentUri = pdfUri
                            } catch (e: Exception) {
                                showErrorMessage(e.message ?: "Unknown Error")
                            }
                        }
                    }
                }
            }
        )
    }
}