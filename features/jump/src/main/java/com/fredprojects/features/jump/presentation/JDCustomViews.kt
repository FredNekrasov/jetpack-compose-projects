package com.fredprojects.features.jump.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.jump.domain.utils.SortType

@Composable
internal fun JDSortingSection(
    sortType: SortType,
    onSortingChange: (SortType) -> Unit
) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        FredRadioButton(stringResource(R.string.ascendingSort), sortType is SortType.Ascending) { onSortingChange(SortType.Ascending) }
        FredRadioButton(stringResource(R.string.descendingSort), sortType is SortType.Descending) { onSortingChange(SortType.Descending) }
    }
}
@Composable
internal fun JDIconButton(icon: ImageVector, onClick: Action) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.padding(4.dp).border(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onBackground,
            shape = MaterialTheme.shapes.small
        )
    ) {
        Icon(imageVector = icon, contentDescription = icon.toString())
    }
}