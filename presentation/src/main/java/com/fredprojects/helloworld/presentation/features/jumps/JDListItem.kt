package com.fredprojects.helloworld.presentation.features.jumps

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.presentation.R
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import com.fredprojects.helloworld.presentation.core.*

@Composable
internal fun JDListItem(jumpData: JumpData, onDeleteClick: Action, modifier: Modifier = Modifier) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), MaterialTheme.colors.error, MaterialTheme.colors.onError)
        Column(Modifier.fillMaxSize().padding(16.dp).padding(end = 32.dp)) {
            FredText("${stringResource(R.string.date)}: ${jumpData.date}", color = MaterialTheme. colors. background)
            Spacer(modifier = Modifier.height(2.dp))
            FredText("${stringResource(R.string.count)}: ${jumpData.count}", color = MaterialTheme. colors. background)
        }
        FredIconButton(onDeleteClick, Icons.Outlined.Delete, Modifier.align(Alignment.BottomEnd))
    }
}