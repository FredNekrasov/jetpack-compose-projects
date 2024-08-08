package com.fredprojects.features.fibonacci.impl

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fredprojects.core.ui.*

@Composable
fun AnswerFibScreen(result: String, goBack: Action) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FredText(result)
        FredIconButton(
            goBack,
            Icons.AutoMirrored.Default.KeyboardArrowLeft,
            Modifier.border(ButtonDefaults.outlinedBorder).align(Alignment.Start),
            MaterialTheme.colors.onBackground
        )
    }
}