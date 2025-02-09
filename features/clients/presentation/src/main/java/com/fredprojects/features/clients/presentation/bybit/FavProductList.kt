package com.fredprojects.features.clients.presentation.bybit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.clients.domain.bybit.models.BBInfo

@Composable
fun FavProductList(
    list: List<BBInfo>,
    onUpdate: (BBInfo) -> Unit,
    goBack: Action
) {
    Scaffold(topBar = { FredTopBar(goBack) }) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding), Arrangement.Center, Alignment.CenterHorizontally) {
            Spacer(Modifier.height(16.dp))
            FredHeaderText(stringResource(R.string.favourites), MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))
            LazyColumn(Modifier.fillMaxSize()) {
                items(list) { bbInfo ->
                    if(!bbInfo.isFav) return@items
                    var isFav by rememberSaveable { mutableStateOf(bbInfo.isFav) }
                    ProductListItem(
                        bbInfo = bbInfo,
                        isFavorite = isFav,
                        onFavouriteChange = {
                            isFav = it
                            bbInfo.isFav = it
                            onUpdate(bbInfo)
                        },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                    )
                    Spacer(Modifier.height(2.dp))
                }
            }
        }
    }
}