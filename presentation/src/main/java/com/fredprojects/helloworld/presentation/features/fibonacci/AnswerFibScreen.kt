package com.fredprojects.helloworld.presentation.features.fibonacci

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fredprojects.helloworld.presentation.core.*

@Composable
fun AnswerFibScreen(result: String, goBack: Action) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()), Arrangement.Center, Alignment.CenterHorizontally) {
        FredText(result)
        FredIconButton(goBack, Icons.Default.ArrowBackIosNew, Modifier.align(Alignment.Start))
    }
}