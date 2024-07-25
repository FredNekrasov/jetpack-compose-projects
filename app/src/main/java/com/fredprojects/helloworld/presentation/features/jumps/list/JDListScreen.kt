package com.fredprojects.helloworld.presentation.features.jumps.list

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.presentation.features.jumps.SortingSection
import com.fredprojects.helloworld.presentation.features.jumps.list.vm.JDEvents
import com.fredprojects.helloworld.presentation.features.jumps.list.vm.JDState

@Composable
fun JDListScreen(
    state: JDState,
    onEvent: (JDEvents) -> Unit
) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        AnimatedVisibility(
            state.isSortingSectionVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            SortingSection(state.sortType) { onEvent(JDEvents.Sort(it)) }
        }
        LazyColumn(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            items(state.jds) { jumpData ->
                Spacer(Modifier.height(8.dp))
                JDListItem(
                    jumpData = jumpData,
                    onDeleteClick = { onEvent(JDEvents.DeleteJD(jumpData)) },
                    Modifier.fillMaxWidth().padding(8.dp).clickable {
                        onEvent(JDEvents.GetJD(jumpData.id ?: 0))
                        onEvent(JDEvents.SwitchingDialog)
                    }
                )
            }
        }
    }
    if(state.isShowDialog) JDDialog(state, onEvent)
}