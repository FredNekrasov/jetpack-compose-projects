package com.fredprojects.features.clients.presentation.math

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.features.clients.domain.math.models.MathModel

@Composable
internal fun MathInfoListItem(mathInfo: MathModel, modifier: Modifier = Modifier) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), MaterialTheme.colors.secondaryVariant, MaterialTheme.colors.onSecondary)
        Column(Modifier.fillMaxWidth().padding(16.dp)) {
            FredText(mathInfo.expression)
            Spacer(Modifier.height(2.dp))
            FredText(mathInfo.result)
        }
    }
}