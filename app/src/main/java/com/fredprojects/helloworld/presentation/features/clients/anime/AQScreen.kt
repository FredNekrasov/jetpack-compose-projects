package com.fredprojects.helloworld.presentation.features.clients.anime

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.domain.core.utils.ConnectionStatus
import com.fredprojects.helloworld.domain.features.clients.common.AnimeQuote
import com.fredprojects.helloworld.presentation.core.*

@Composable
fun AQScreen(
    state: ConnectionStatus<AnimeQuote>,
    onSearch: (String) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }
    var isQueryCorrect by rememberSaveable { mutableStateOf(true) }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()), Arrangement.Center, Alignment.CenterHorizontally) {
        Spacer(Modifier.height(16.dp))
        FredHeaderText(stringResource(R.string.anime), MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        FredTextField(query, { query = it }, R.string.enterAnime, R.string.error, isQueryCorrect, ImeAction.Done)
        Spacer(Modifier.height(4.dp))
        FredButton(
            {
                isQueryCorrect = query.isNotEmpty()
                if (isQueryCorrect) onSearch(query)
            },
            stringResource(R.string.search)
        )
        Spacer(Modifier.height(2.dp))
        FredText(stringResource(state.status.getString()))
        if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) AQScreenContentPortrait(state) else AQScreenContentLandscape(state)
    }
}
@Composable
private fun AQScreenContentPortrait(state: ConnectionStatus<AnimeQuote>) {
    state.list.forEach {
        Spacer(Modifier.height(4.dp))
        AQListItem(it, Modifier.fillMaxWidth())
    }
}
@Composable
private fun AQScreenContentLandscape(state: ConnectionStatus<AnimeQuote>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(state.list) {
            AQListItem(it, Modifier.wrapContentSize())
        }
    }
}