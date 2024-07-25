package com.fredprojects.helloworld.presentation.features.clients.anime

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.presentation.R
import com.fredprojects.helloworld.domain.features.clients.common.AnimeQuote
import com.fredprojects.helloworld.presentation.core.FredCard
import com.fredprojects.helloworld.presentation.core.FredText

@Composable
internal fun AQListItem(aq: AnimeQuote, modifier: Modifier) {
    Box(modifier) {
        FredCard(Modifier.matchParentSize(), MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.onSecondary)
        Column(Modifier.wrapContentSize().padding(16.dp)) {
            FredText("${stringResource(R.string.anime)}: ${aq.anime}")
            Spacer(modifier = Modifier.height(8.dp))
            FredText("${stringResource(R.string.character)}: ${aq.character}")
            Spacer(modifier = Modifier.height(8.dp))
            FredText("${stringResource(R.string.quote)}: ${aq.quote}")
        }
    }
}