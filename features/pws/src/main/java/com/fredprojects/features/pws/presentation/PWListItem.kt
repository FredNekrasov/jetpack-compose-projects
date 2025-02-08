package com.fredprojects.features.pws.presentation

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.pws.domain.models.PracticalWork

@Composable
internal fun PWListItem(pw: PracticalWork, modifier: Modifier, onDelete: Action) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary)
        if(LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
            LandscapePWListItemContent(pw)
        } else {
            PortraitPWListItemContent(pw)
        }
        FredIconButton(onDelete, icon = Icons.Outlined.Delete, Modifier.align(Alignment.BottomEnd))
    }
}
@Composable
private fun PortraitPWListItemContent(pw: PracticalWork) {
    Column(Modifier.fillMaxWidth().padding(16.dp).padding(end = 32.dp)) {
        FredText("${stringResource(R.string.pw)}: ${pw.pwName}", color = MaterialTheme.colorScheme.background)
        Spacer(modifier = Modifier.height(2.dp))
        FredText("${stringResource(R.string.student)}: ${pw.student}", color = MaterialTheme.colorScheme.background)
        Spacer(modifier = Modifier.height(2.dp))
        FredText("${stringResource(R.string.variant)}: ${pw.variant}", color = MaterialTheme.colorScheme.background)
        Spacer(modifier = Modifier.height(2.dp))
        FredText("${stringResource(R.string.lvl)}: ${pw.level}", color = MaterialTheme.colorScheme.background)
        Spacer(modifier = Modifier.height(2.dp))
        FredText("${stringResource(R.string.date)}: ${pw.date}", color = MaterialTheme.colorScheme.background)
        Spacer(modifier = Modifier.height(2.dp))
        FredText("${stringResource(R.string.mark)}: ${pw.mark}", color = MaterialTheme.colorScheme.background)
        AsyncImage(pw.image.toUri(), pw.image, Modifier.fillMaxWidth(0.5f))
    }
}
@Composable
private fun LandscapePWListItemContent(pw: PracticalWork) {
    Row(Modifier.fillMaxWidth().padding(16.dp).padding(end = 32.dp), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
        Column(Modifier.fillMaxHeight()) {
            FredText("${stringResource(R.string.pw)}: ${pw.pwName}", color = MaterialTheme.colorScheme.background)
            Spacer(modifier = Modifier.height(2.dp))
            FredText("${stringResource(R.string.student)}: ${pw.student}", color = MaterialTheme.colorScheme.background)
            Spacer(modifier = Modifier.height(2.dp))
            FredText("${stringResource(R.string.variant)}: ${pw.variant}", color = MaterialTheme.colorScheme.background)
            Spacer(modifier = Modifier.height(2.dp))
            FredText("${stringResource(R.string.lvl)}: ${pw.level}", color = MaterialTheme.colorScheme.background)
            Spacer(modifier = Modifier.height(2.dp))
            FredText("${stringResource(R.string.date)}: ${pw.date}", color = MaterialTheme.colorScheme.background)
            Spacer(modifier = Modifier.height(2.dp))
            FredText("${stringResource(R.string.mark)}: ${pw.mark}", color = MaterialTheme.colorScheme.background)
        }
        AsyncImage(pw.image.toUri(), pw.image, Modifier.fillMaxWidth(0.5f).padding(16.dp))
    }
}