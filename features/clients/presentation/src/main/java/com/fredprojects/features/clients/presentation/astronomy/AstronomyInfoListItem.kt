package com.fredprojects.features.clients.presentation.astronomy

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.features.clients.domain.astronomy.models.AstronomyInfo

@Composable
internal fun AstronomyInfoListItem(info: AstronomyInfo, modifier: Modifier) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.primaryContainer)
        Column(Modifier.fillMaxWidth().padding(16.dp)) {
            FredHeaderText(info.name, textStyle = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(4.dp))
            FredText(info.references)
            Spacer(Modifier.height(2.dp))
            FredText(info.ra)
            Spacer(Modifier.height(2.dp))
            FredText(info.dec)
            Spacer(Modifier.height(2.dp))
            FredText(info.radius.toString())
        }
    }
}