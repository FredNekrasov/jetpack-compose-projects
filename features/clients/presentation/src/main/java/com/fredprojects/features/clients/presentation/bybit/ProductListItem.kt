package com.fredprojects.features.clients.presentation.bybit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.features.clients.domain.bybit.models.BBInfo
import com.fredprojects.features.clients.presentation.FavCheckBox

@Composable
internal fun ProductListItem(
    bbInfo: BBInfo,
    isFavorite: Boolean,
    onFavouriteChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), MaterialTheme.colors.onBackground, MaterialTheme.colors.background, cutCornerSize = 0.dp)
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            FredHeaderText(bbInfo.title, MaterialTheme.typography.h5, color = MaterialTheme.colors.background)
            Spacer(modifier = Modifier.height(8.dp))
            FredText(bbInfo.description, color = MaterialTheme.colors.background)
            Spacer(modifier = Modifier.height(8.dp))
            FredText("${bbInfo.type.title}: ${bbInfo.type.key}", color = MaterialTheme.colors.background)
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(Modifier.fillMaxWidth()) {
                items(bbInfo.tags) {
                    FredText(it, color = MaterialTheme.colors.background)
                    Spacer(Modifier.width(2.dp))
                }
            }
            FredText(bbInfo.url, color = MaterialTheme.colors.background)
            FavCheckBox(isFavorite, onFavouriteChange)
        }
    }
}