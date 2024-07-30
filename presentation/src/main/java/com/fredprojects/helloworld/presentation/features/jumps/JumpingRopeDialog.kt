package com.fredprojects.helloworld.presentation.features.jumps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fredprojects.helloworld.presentation.core.Action
import com.fredprojects.helloworld.presentation.core.FredIconButton

@Composable
fun JumpingRopeDialog(countOfJumps: Int, closeDialog: Action) {
    Dialog(closeDialog) {
        Column(
            Modifier.fillMaxSize().padding(8.dp).background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StickMan(countOfJumps)
            FredIconButton(
                closeDialog,
                Icons.AutoMirrored.Default.KeyboardArrowLeft,
                Modifier.align(Alignment.Start),
                MaterialTheme.colors.onBackground
            )
        }
    }
}