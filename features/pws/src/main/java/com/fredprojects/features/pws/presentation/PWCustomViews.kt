package com.fredprojects.features.pws.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.pws.domain.utils.SortType
import com.fredprojects.features.pws.domain.utils.SortingPW

@Composable
internal fun SortingSection(
    sortingPW: SortingPW = SortingPW.Date(SortType.Descending),
    onSortingChange: (SortingPW) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        LazyRow(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            item {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                    FredRadioButton(stringResource(R.string.pw), sortingPW is SortingPW.PW) { onSortingChange(SortingPW.PW(sortingPW.sortType)) }
                    FredRadioButton(stringResource(R.string.student), sortingPW is SortingPW.Student) { onSortingChange(SortingPW.Student(sortingPW.sortType)) }
                    FredRadioButton(stringResource(R.string.variant), sortingPW is SortingPW.Variant) { onSortingChange(SortingPW.Variant(sortingPW.sortType)) }
                }
            }
            item {
                Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                    FredRadioButton(stringResource(R.string.lvl), sortingPW is SortingPW.LVL) { onSortingChange(SortingPW.LVL(sortingPW.sortType)) }
                    FredRadioButton(stringResource(R.string.date), sortingPW is SortingPW.Date) { onSortingChange(SortingPW.Date(sortingPW.sortType)) }
                    FredRadioButton(stringResource(R.string.mark), sortingPW is SortingPW.Mark) { onSortingChange(SortingPW.Mark(sortingPW.sortType)) }
                }
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
            FredRadioButton(stringResource(R.string.ascendingSort), sortingPW.sortType is SortType.Ascending) { onSortingChange(sortingPW.copy(SortType.Ascending)) }
            FredRadioButton(stringResource(R.string.descendingSort), sortingPW.sortType is SortType.Descending) { onSortingChange(sortingPW.copy(SortType.Descending)) }
        }
    }
}
@Composable
internal fun PWSearchBar(onSearch: (String) -> Unit) {
    var query by rememberSaveable { mutableStateOf("") }
    TextField(
        value = query,
        onValueChange = { query = it },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        label = { FredText(stringResource(R.string.search)) },
        trailingIcon = { Icon(Icons.Default.Search, stringResource(R.string.search)) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch(query) }),
    )
}
@Composable
internal fun TakePhotoButton(onTakePhoto: Action) {
    Button(
        onClick = onTakePhoto,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onBackground, MaterialTheme.colorScheme.background)
    ) {
        FredText(stringResource(R.string.takePicture))
        Spacer(Modifier.width(2.dp))
        Icon(Icons.Default.Place, Icons.Default.Place.toString())
    }
}
// creating copy of sortingPW to be able to change sortType
private fun SortingPW.copy(sortType: SortType): SortingPW = when(this) {
    is SortingPW.PW -> SortingPW.PW(sortType)
    is SortingPW.Student -> SortingPW.Student(sortType)
    is SortingPW.Variant -> SortingPW.Variant(sortType)
    is SortingPW.LVL -> SortingPW.LVL(sortType)
    is SortingPW.Date -> SortingPW.Date(sortType)
    is SortingPW.Mark -> SortingPW.Mark(sortType)
}