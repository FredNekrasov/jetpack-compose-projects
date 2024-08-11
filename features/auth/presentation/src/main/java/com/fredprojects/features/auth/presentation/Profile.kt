package com.fredprojects.features.auth.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.FredHeaderText
import com.fredprojects.core.ui.R
import com.fredprojects.features.auth.domain.models.User
import com.fredprojects.features.auth.presentation.vm.AuthEvents

@Composable
fun Profile(
    userData: User?,
    onDelete: (AuthEvents) -> Unit,
    toEditScreen: () -> Unit
) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(8.dp))
        FredHeaderText(stringResource(R.string.profile), MaterialTheme.typography.h5)
        Spacer(Modifier.height(16.dp))
        userData?.let {
            UserInfo(it, { onDelete(AuthEvents.DeleteUser) }, toEditScreen, Modifier.fillMaxWidth().padding(8.dp))
        }
    }
}