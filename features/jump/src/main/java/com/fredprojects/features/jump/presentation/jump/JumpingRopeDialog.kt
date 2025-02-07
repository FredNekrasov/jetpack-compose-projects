package com.fredprojects.features.jump.presentation.jump

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fredprojects.core.ui.Action
import com.fredprojects.core.ui.FredIconButton

@Composable
fun JumpingRopeDialog(countOfJumps: Int, closeDialog: Action) {
    Dialog(closeDialog) {
        Column(
            Modifier.fillMaxSize().padding(8.dp).background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StickMan(countOfJumps)
            FredIconButton(
                closeDialog,
                Icons.AutoMirrored.Default.KeyboardArrowLeft,
                Modifier.align(Alignment.Start),
                MaterialTheme.colorScheme.onBackground
            )
        }
    }
}