package com.fredprojects.features.clients.presentation.bybit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.R
import com.fredprojects.core.ui.*
import com.fredprojects.features.clients.domain.bybit.models.BBInfo

@Composable
fun ProductList(
    list: List<BBInfo>,
    onUpdate: (BBInfo) -> Unit,
    navButtons: @Composable () -> Unit
) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Spacer(Modifier.height(16.dp))
        FredHeaderText(stringResource(R.string.shop), MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        LazyColumn(Modifier.fillMaxSize()) {
            items(list) { bbInfo ->
                var isFav by rememberSaveable { mutableStateOf(bbInfo.isFav) }
                ProductListItem(
                    bbInfo,
                    isFav,
                    {
                        isFav = it
                        bbInfo.isFav = it
                        onUpdate(bbInfo)
                    },
                    Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                )
                Spacer(Modifier.height(2.dp))
            }
            item { navButtons() }
        }
    }
}