package com.fredprojects.helloworld.presentation.features.clients.astronomy

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.domain.core.utils.ActionStatus
import com.fredprojects.helloworld.domain.core.utils.ConnectionStatus
import com.fredprojects.helloworld.domain.features.clients.astronomy.models.AstronomyInfo
import com.fredprojects.helloworld.presentation.core.*

@Composable
fun AstronomyInfoScreen(
    state: ConnectionStatus<AstronomyInfo>,
    onSearch: (String, String, String) -> Unit
) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Spacer(Modifier.height(16.dp))
        AstronomyScreenContent(state.status, onSearch)
        if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) AstronomyInfoScreenContentPortrait(state) else AstronomyInfoScreenContentLandscape(state)
    }
}
@Composable
private fun AstronomyScreenContent(
    status: ActionStatus,
    onSearch: (String, String, String) -> Unit
) {
    var ra by rememberSaveable { mutableStateOf("") }
    var isRACorrect by rememberSaveable { mutableStateOf(true) }
    var dec by rememberSaveable { mutableStateOf("") }
    var isDesCorrect by rememberSaveable { mutableStateOf(true) }
    var radius by rememberSaveable { mutableStateOf("") }
    var isRadiusCorrect by rememberSaveable { mutableStateOf(true) }
    FredTextField(ra, { ra = it }, R.string.enterRA, R.string.error, isRACorrect)
    FredTextField(dec, { dec = it }, R.string.enterDec, R.string.error, isDesCorrect)
    FredTextField(radius, { if ((it.toFloatOrNull() != null) || it.isEmpty() || (it == "-")) radius = it }, R.string.enterRadius, R.string.error, isRadiusCorrect, ImeAction.Done, KeyboardType.Phone)
    FredButton(
        {
            isRACorrect = ra.isNotEmpty()
            isDesCorrect = dec.isNotEmpty()
            isRadiusCorrect = radius.isNotEmpty()
            if(isRACorrect && isDesCorrect && isRadiusCorrect) onSearch(ra, dec, radius)
        },
        stringResource(R.string.search)
    )
    Spacer(Modifier.height(2.dp))
    FredText(stringResource(status.getString()))

}
@Composable
private fun AstronomyInfoScreenContentPortrait(state: ConnectionStatus<AstronomyInfo>) {
    state.list.forEach {
        Spacer(Modifier.height(4.dp))
        AstronomyInfoListItem(it, Modifier.fillMaxWidth())
    }
}
@Composable
private fun AstronomyInfoScreenContentLandscape(state: ConnectionStatus<AstronomyInfo>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(state.list) {
            AstronomyInfoListItem(it, Modifier.wrapContentSize())
        }
    }
}