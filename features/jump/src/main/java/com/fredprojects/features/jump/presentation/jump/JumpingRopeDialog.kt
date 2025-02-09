package com.fredprojects.features.jump.presentation.jump

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fredprojects.core.ui.Action

@Composable
fun JumpingRopeDialog(countOfJumps: Int, closeDialog: Action) {
    Dialog(closeDialog) {
        Column(
            Modifier.fillMaxSize()
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.background)
                .clickable(onClick = closeDialog),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StickMan(countOfJumps)
        }
    }
}