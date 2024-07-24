package com.fredprojects.helloworld.presentation.features.jumps

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.domain.core.utils.SortType
import com.fredprojects.helloworld.presentation.core.Action
import com.fredprojects.helloworld.presentation.core.FredRadioButton

@Composable
internal fun SortingSection(
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
        Modifier.padding(4.dp).border(2.dp, MaterialTheme.colorScheme.onBackground, MaterialTheme.shapes.small)
    ) {
        Icon(icon, icon.toString())
    }
}