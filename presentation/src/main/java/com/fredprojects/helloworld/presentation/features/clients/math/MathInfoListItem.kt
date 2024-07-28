package com.fredprojects.helloworld.presentation.features.clients.math

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.domain.features.clients.common.MathModel
import com.fredprojects.helloworld.presentation.core.FredCard
import com.fredprojects.helloworld.presentation.core.FredText

@Composable
internal fun MathInfoListItem(mathInfo: MathModel, modifier: Modifier = Modifier) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize().border(2.dp, MaterialTheme.colorScheme.onSurface, MaterialTheme.shapes.small), MaterialTheme.colorScheme.surface, MaterialTheme.colorScheme.onSurface)
        Column(Modifier.fillMaxWidth().padding(16.dp)) {
            FredText(mathInfo.expression)
            Spacer(Modifier.height(2.dp))
            FredText(mathInfo.result)
        }
    }
}