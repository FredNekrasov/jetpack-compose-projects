package com.fredprojects.features.jump.presentation.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.jump.domain.models.JumpData

@Composable
internal fun JDListItem(jumpData: JumpData, onDeleteClick: Action, modifier: Modifier = Modifier) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), MaterialTheme.colorScheme.error, MaterialTheme.colorScheme.onError)
        Column(Modifier.fillMaxSize().padding(16.dp).padding(end = 32.dp)) {
            FredText("${stringResource(R.string.date)}: ${jumpData.date}", color = MaterialTheme. colorScheme. background)
            Spacer(modifier = Modifier.height(2.dp))
            FredText("${stringResource(R.string.count)}: ${jumpData.count}", color = MaterialTheme. colorScheme. background)
        }
        FredIconButton(onDeleteClick, Icons.Outlined.Delete, Modifier.align(Alignment.BottomEnd))
    }
}