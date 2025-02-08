package com.fredprojects.features.clients.presentation.astronomy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.clients.domain.astronomy.models.AstronomyInfo
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
import com.fredprojects.features.clients.presentation.getString

@Composable
fun AstronomyInfoScreen(
    state: ConnectionStatus<AstronomyInfo>,
    onSearch: (String, String, String) -> Unit
) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        AstronomyInfoScreenTextFields(onSearch)
        FredText(stringResource(state.status.getString()))
        LazyColumn(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly) {
            items(state.list, key = { it.hashCode() }) {
                AstronomyInfoListItem(it, Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 2.dp))
            }
        }
    }
}
@Composable
private fun AstronomyInfoScreenTextFields(onSearch: (String, String, String) -> Unit) {
    var ra by rememberSaveable { mutableStateOf("") }
    var dec by rememberSaveable { mutableStateOf("") }
    var radius by rememberSaveable { mutableStateOf("") }
    var isValuesCorrect by rememberSaveable { mutableStateOf(true) }
    FredTextField(ra, { ra = it }, R.string.enterRA, isValuesCorrect)
    FredTextField(dec, { dec = it }, R.string.enterDec, isValuesCorrect)
    FredTextField(
        radius,
        { if((it.toFloatOrNull() != null) || it.isEmpty() || (it == "-")) radius = it },
        R.string.enterRadius,
        isValuesCorrect,
        ImeAction.Done,
        KeyboardType.Decimal
    )
    if(!isValuesCorrect) FredText(stringResource(R.string.error), color = MaterialTheme.colorScheme.error)
    Spacer(Modifier.height(4.dp))
    FredButton(
        {
            isValuesCorrect = ra.isNotBlank() && dec.isNotBlank() && (radius.isNotBlank() && (radius.toFloatOrNull() != null))
            if(isValuesCorrect) onSearch(ra, dec, radius)
        },
        stringResource(R.string.search)
    )
}