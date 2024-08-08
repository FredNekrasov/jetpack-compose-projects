package com.fredprojects.features.jumps.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.jumps.domain.utils.SortType

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
internal fun JDIconButton(onClick: Action, icon: ImageVector) {
    IconButton(
        onClick,
        Modifier.padding(4.dp).border(
            width = 2.dp,
            color = MaterialTheme.colors.onBackground,
            shape = MaterialTheme.shapes.small
        )
    ) {
        Icon(icon, icon.toString())
    }
}