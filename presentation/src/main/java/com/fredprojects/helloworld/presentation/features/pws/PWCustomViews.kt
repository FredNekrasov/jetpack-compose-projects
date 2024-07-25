package com.fredprojects.helloworld.presentation.features.pws

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.domain.core.utils.SortType
import com.fredprojects.helloworld.domain.features.pws.utils.SortingPW
import com.fredprojects.helloworld.presentation.R
import com.fredprojects.helloworld.presentation.core.*

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
        Spacer(Modifier.height(4.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
            FredRadioButton(stringResource(R.string.ascendingSort), sortingPW.sortType is SortType.Ascending) { onSortingChange(sortingPW.copy(SortType.Ascending)) }
            FredRadioButton(stringResource(R.string.descendingSort), sortingPW.sortType is SortType.Descending) { onSortingChange(sortingPW.copy(SortType.Descending)) }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PWSearchBar(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchItem by rememberSaveable { mutableStateOf("") }
    var isActive by rememberSaveable { mutableStateOf(false) }
    SearchBar(
        query = searchItem,
        onQueryChange = { searchItem = it },
        onSearch = {
            onSearch(it)
            isActive = false
        },
        active = isActive,
        onActiveChange = { isActive = it },
        modifier = modifier,
        placeholder = { FredText(stringResource(R.string.search)) },
        trailingIcon = { Icon(Icons.Default.Search, searchItem) }
    ) {
        CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
    }
}
@Composable
internal fun TakePhotoButton(onTakePhoto: Action) {
    Button(onClick = onTakePhoto) {
        FredText(stringResource(R.string.takePicture))
        Spacer(Modifier.width(4.dp))
        Icon(Icons.Default.AddAPhoto, Icons.Default.AddAPhoto.toString())
    }
}