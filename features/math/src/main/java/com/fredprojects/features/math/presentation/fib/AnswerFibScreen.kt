package com.fredprojects.features.math.presentation.fib

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
            Modifier.border(ButtonDefaults.outlinedButtonBorder()).align(Alignment.Start),
            MaterialTheme.colorScheme.onBackground
        )
    }
}