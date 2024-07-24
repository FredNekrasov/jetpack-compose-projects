package com.fredprojects.helloworld.presentation.features.pws

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.presentation.core.*

@Composable
internal fun PWListItem(
    pw: PracticalWork,
    modifier: Modifier,
    onDelete: Action
) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary)
        if(LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
            LandscapePWListItemContent(pw)
        } else {
            PortraitPWListItemContent(pw)
        }
        FredIconButton(onDelete, icon = Icons.Default.Delete, Modifier.align(Alignment.BottomEnd))
    }
}
@Composable
private fun PortraitPWListItemContent(pw: PracticalWork) {
    Column(Modifier.fillMaxSize().padding(16.dp).padding(end = 32.dp)) {
        FredText("${stringResource(R.string.pw)}: ${pw.pwName}")
        Spacer(modifier = Modifier.height(4.dp))
        FredText("${stringResource(R.string.student)}: ${pw.student}")
        Spacer(modifier = Modifier.height(4.dp))
        FredText("${stringResource(R.string.variant)}: ${pw.variant}")
        Spacer(modifier = Modifier.height(4.dp))
        FredText("${stringResource(R.string.lvl)}: ${pw.level}")
        Spacer(modifier = Modifier.height(4.dp))
        FredText("${stringResource(R.string.date)}: ${pw.date}")
        Spacer(modifier = Modifier.height(4.dp))
        FredText("${stringResource(R.string.mark)}: ${pw.mark}")
        Image(rememberAsyncImagePainter(pw.image.toUri()), pw.image, Modifier.wrapContentSize())
    }
}
@Composable
private fun LandscapePWListItemContent(pw: PracticalWork) {
    Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            FredText("${stringResource(R.string.pw)}: ${pw.pwName}")
            Spacer(modifier = Modifier.height(4.dp))
            FredText("${stringResource(R.string.student)}: ${pw.student}")
            Spacer(modifier = Modifier.height(4.dp))
            FredText("${stringResource(R.string.variant)}: ${pw.variant}")
            Spacer(modifier = Modifier.height(4.dp))
            FredText("${stringResource(R.string.lvl)}: ${pw.level}")
            Spacer(modifier = Modifier.height(4.dp))
            FredText("${stringResource(R.string.date)}: ${pw.date}")
            Spacer(modifier = Modifier.height(4.dp))
            FredText("${stringResource(R.string.mark)}: ${pw.mark}")
        }
        Image(rememberAsyncImagePainter(pw.image.toUri()), pw.image, Modifier.wrapContentSize().padding(16.dp).padding(end = 32.dp))
    }
}