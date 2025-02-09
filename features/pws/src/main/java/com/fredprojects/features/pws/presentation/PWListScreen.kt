package com.fredprojects.features.pws.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
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
    goBack: Action,
    toUpsertPWScreen: (Int?) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val deleteMessage = stringResource(R.string.deletedRecord)
    val cancel = stringResource(R.string.cancel)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            FredTopBar(goBack) {
                FredIconButton({ onEvent(PWEvents.ToggleSortSection) }, if(!state.isSortingSectionVisible) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp)
            }
        },
        floatingActionButton = { FredFloatingActionButton(Icons.Outlined.Add) { toUpsertPWScreen(null) } },
        contentWindowInsets = WindowInsets.captionBar
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
                items(state.pws, key = { it.hashCode() }) { pw ->
                    PWListItem(pw, Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 2.dp).clickable { toUpsertPWScreen(pw.id) }) {
                        onEvent(PWEvents.DeletePW(pw))
                        scope.launch {
                            val result = snackbarHostState.showSnackbar(deleteMessage, actionLabel = cancel)
                            if(result == SnackbarResult.ActionPerformed) onEvent(PWEvents.RestorePW)
                        }
                    }
                }
            }
        }
    }
}