package com.fredprojects.helloworld.presentation.features.clients.astronomy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.domain.core.utils.ConnectionStatus
import com.fredprojects.helloworld.domain.features.clients.astronomy.models.AstronomyInfo
import com.fredprojects.helloworld.presentation.R
import com.fredprojects.helloworld.presentation.core.*

@Composable
fun AstronomyInfoScreen(
    state: ConnectionStatus<AstronomyInfo>,
    onSearch: (String, String, String) -> Unit
) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        AstronomyInfoScreenTextFields(onSearch)
        FredText(stringResource(state.status.getString()))
        LazyColumn(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly) {
            items(state.list) {
                AstronomyInfoListItem(it, Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 2.dp))
            }
        }
    }
}
@Composable
private fun AstronomyInfoScreenTextFields(onSearch: (String, String, String) -> Unit) {
    var ra by rememberSaveable { mutableStateOf("") }
    var isRACorrect by rememberSaveable { mutableStateOf(true) }
    var dec by rememberSaveable { mutableStateOf("") }
    var isDecCorrect by rememberSaveable { mutableStateOf(true) }
    var radius by rememberSaveable { mutableStateOf("") }
    var isRadiusCorrect by rememberSaveable { mutableStateOf(true) }
    FredTextField(ra, { ra = it }, R.string.enterRA, isRACorrect)
    if(!isRACorrect) FredText(stringResource(R.string.error), color = MaterialTheme.colors.error)
    FredTextField(dec, { dec = it }, R.string.enterDec, isDecCorrect)
    if(!isDecCorrect) FredText(stringResource(R.string.error), color = MaterialTheme.colors.error)
    FredTextField(
        radius,
        { if((it.toFloatOrNull() != null) || it.isEmpty() || (it == "-")) radius = it },
        R.string.enterRadius,
        isRadiusCorrect,
        ImeAction.Done,
        KeyboardType.Decimal
    )
    if(!isRadiusCorrect) FredText(stringResource(R.string.error), color = MaterialTheme.colors.error)
    Spacer(Modifier.height(4.dp))
    FredButton(
        {
            isRACorrect = ra.isNotEmpty()
            isDecCorrect = dec.isNotEmpty()
            isRadiusCorrect = radius.isNotEmpty() && (radius.toFloatOrNull() != null)
            if(isRACorrect && isDecCorrect && isRadiusCorrect) onSearch(ra, dec, radius)
        },
        stringResource(R.string.search)
    )
}