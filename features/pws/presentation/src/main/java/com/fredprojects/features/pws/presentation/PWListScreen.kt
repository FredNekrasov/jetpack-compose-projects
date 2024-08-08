package com.fredprojects.features.pws.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.pws.presentation.vm.PWEvents
import com.fredprojects.features.pws.presentation.vm.PWState
import kotlinx.coroutines.launch

@Composable
fun PWListScreen(
    state: PWState,
    onEvent: (PWEvents) -> Unit,
    toUpsertPWScreen: (Int?) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val deleteMessage = stringResource(R.string.deletedRecord)
    val cancel = stringResource(R.string.cancel)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = { FredTopBar(true, { onEvent(PWEvents.ToggleSortSection) }, state.isSortingSectionVisible) },
        floatingActionButton = { FredFloatingActionButton(Icons.Outlined.Add) { toUpsertPWScreen(null) } }
    ) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(
                state.isSortingSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) { SortingSection(state.sortingPW) { onEvent(PWEvents.Sort(it)) } }
            Spacer(Modifier.height(4.dp))
            PWSearchBar { onEvent(PWEvents.SearchPW(it)) }
            Spacer(Modifier.height(4.dp))
            LazyColumn(Modifier.fillMaxWidth()) {
                items(state.pws) { pw ->
                    PWListItem(pw, Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 2.dp).clickable { toUpsertPWScreen(pw.id) }) {
                        onEvent(PWEvents.DeletePW(pw))
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(deleteMessage, actionLabel = cancel)
                            if(result == SnackbarResult.ActionPerformed) onEvent(PWEvents.RestorePW)
                        }
                    }
                }
            }
        }
    }
}