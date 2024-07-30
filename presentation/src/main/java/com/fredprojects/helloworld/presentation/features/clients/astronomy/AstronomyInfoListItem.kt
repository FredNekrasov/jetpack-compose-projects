package com.fredprojects.helloworld.presentation.features.clients.astronomy

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.domain.features.clients.astronomy.models.AstronomyInfo
import com.fredprojects.helloworld.presentation.core.*

@Composable
internal fun AstronomyInfoListItem(info: AstronomyInfo, modifier: Modifier) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), MaterialTheme.colors.primary, MaterialTheme.colors.primarySurface)
        Column(Modifier.fillMaxWidth().padding(16.dp)) {
            FredHeaderText(info.name, textStyle = MaterialTheme.typography.h5)
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