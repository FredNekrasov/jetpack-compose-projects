package com.fredprojects.helloworld.presentation.features.pws

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.presentation.features.pws.vm.PWEvents
import com.fredprojects.helloworld.presentation.features.pws.vm.PWState

@Composable
fun PWListScreen(
    state: PWState,
    onEvent: (PWEvents) -> Unit,
    onAddOrEdit: (Int?) -> Unit
) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        AnimatedVisibility(
            state.isSortingSectionVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            SortingSection(state.sortingPW) { onEvent(PWEvents.Sort(it)) }
        }
        Spacer(Modifier.height(8.dp))
        PWSearchBar({ onEvent(PWEvents.SearchPW(it)) }, Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        LazyColumn(Modifier.fillMaxSize()) {
            items(state.pws) { pw ->
                PWListItem(
                    pw = pw,
                    modifier = Modifier.fillMaxWidth().clickable { onAddOrEdit(pw.id) },
                    onDelete = { onEvent(PWEvents.DeletePW(pw)) }
                )
                Spacer(Modifier.height(2.dp))
            }
        }
    }
}