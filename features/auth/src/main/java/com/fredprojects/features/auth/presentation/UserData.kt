package com.fredprojects.features.auth.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.auth.domain.models.User

@Composable
internal fun UserInfo(
    user: User,
    onDelete: Action,
    onEdit: Action,
    modifier: Modifier
) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), MaterialTheme.colorScheme.onBackground, MaterialTheme.colorScheme.background)
        Column(modifier.padding(16.dp).padding(horizontal = 32.dp)) {
            FredText("${stringResource(R.string.userName)}: ${user.login}", color = MaterialTheme.colorScheme.background)
            Spacer(Modifier.height(8.dp))
            FredText("${stringResource(R.string.password)}: ${user.password}", color = MaterialTheme.colorScheme.background)
            Spacer(Modifier.height(8.dp))
            FredText("${stringResource(R.string.email)}: ${user.email}", color = MaterialTheme.colorScheme.background)
            Spacer(Modifier.height(8.dp))
            FredText("${stringResource(R.string.name)}: ${user.name}", color = MaterialTheme.colorScheme.background)
            Spacer(Modifier.height(8.dp))
            FredText("${stringResource(R.string.surname)}: ${user.surname}", color = MaterialTheme.colorScheme.background)
            Spacer(Modifier.height(8.dp))
        }
        FredIconButton(onEdit, Icons.Default.Edit, Modifier.align(Alignment.BottomStart))
        FredIconButton(onDelete, Icons.Default.Delete, Modifier.align(Alignment.BottomEnd))
    }
}