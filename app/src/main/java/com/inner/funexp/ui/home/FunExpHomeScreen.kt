package com.inner.funexp.ui.home

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FunExpHomeScreen(
    onNavigateToPdfViewer: (Uri) -> Unit,
) {
    var chosenFileUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val fileChooseLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        chosenFileUri = it
    }

    LaunchedEffect(chosenFileUri) {
        chosenFileUri?.let { onNavigateToPdfViewer(it) }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        FunExpHome(
            onChooseFileButtonClick = {
                fileChooseLauncher.launch("application/pdf")
            }
        )
    }
}

@Composable
fun FunExpHome(
    onChooseFileButtonClick: () -> Unit,
) {
    Button(
        onClick = onChooseFileButtonClick,
    ) {
        Text(text = "파일 선택")
    }
}

@Preview(showBackground = true)
@Composable
fun FunExpHomeScreenPreview() {
    FunExpHomeScreen(
        onNavigateToPdfViewer = {}
    )
}