package com.fredprojects.helloworld.presentation.features.clients.astronomy

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.domain.features.clients.astronomy.models.AstronomyInfo
import com.fredprojects.helloworld.presentation.core.*

@Composable
fun AstronomyInfoListItem(info: AstronomyInfo, modifier: Modifier) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.onPrimaryContainer)
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            FredHeaderText(info.name, MaterialTheme.typography.headlineMedium)
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