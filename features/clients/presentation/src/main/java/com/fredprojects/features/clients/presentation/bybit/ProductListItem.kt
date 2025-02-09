package com.fredprojects.features.clients.presentation.bybit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.MaterialTheme
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
        FredCard(Modifier.matchParentSize(), MaterialTheme.colorScheme.onBackground, MaterialTheme.colorScheme.background, cutCornerSize = 0.dp)
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            FredHeaderText(bbInfo.title, MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.background)
            Spacer(modifier = Modifier.height(8.dp))
            FredText(bbInfo.description, color = MaterialTheme.colorScheme.background)
            Spacer(modifier = Modifier.height(8.dp))
            FredText("${bbInfo.type.title}: ${bbInfo.type.key}", color = MaterialTheme.colorScheme.background)
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(Modifier.fillMaxWidth()) {
                items(bbInfo.tags) {
                    FredText(it, color = MaterialTheme.colorScheme.background)
                    Spacer(Modifier.width(2.dp))
                }
            }
            FredText(bbInfo.url, color = MaterialTheme.colorScheme.background)
            FavCheckBox(isFavorite, onFavouriteChange)
        }
    }
}