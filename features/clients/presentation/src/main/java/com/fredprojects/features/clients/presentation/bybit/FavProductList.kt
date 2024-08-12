package com.fredprojects.features.clients.presentation.bybit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.clients.domain.bybit.models.BBInfo

@Composable
fun FavProductList(
    list: List<BBInfo>, onUpdate: (BBInfo) -> Unit, goBack: Action
) {
    Scaffold(topBar = { FredTopBar(goBack) }) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding), Arrangement.Center, Alignment.CenterHorizontally) {
            FredHeaderText(stringResource(R.string.favourites), MaterialTheme.typography.h4)
            Spacer(Modifier.height(8.dp))
            LazyColumn(Modifier.fillMaxSize()) {
                items(list) { bbInfo ->
                    if(!bbInfo.favorite) return@items
                    var isFavorite by remember { mutableStateOf(bbInfo.favorite) }
                    ProductListItem(
                        bbInfo = bbInfo,
                        isFavorite = isFavorite,
                        onFavouriteChange = {
                            isFavorite = it
                            bbInfo.favorite = it
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