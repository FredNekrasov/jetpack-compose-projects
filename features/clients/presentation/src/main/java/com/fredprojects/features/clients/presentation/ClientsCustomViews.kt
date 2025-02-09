package com.fredprojects.features.clients.presentation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun FavCheckBox(
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Checkbox(
        checked = value,
        onCheckedChange = onValueChange,
        modifier = modifier,
        colors = CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.background,
            uncheckedColor = MaterialTheme.colorScheme.background,
            checkmarkColor = MaterialTheme.colorScheme.onBackground
        )
    )
}