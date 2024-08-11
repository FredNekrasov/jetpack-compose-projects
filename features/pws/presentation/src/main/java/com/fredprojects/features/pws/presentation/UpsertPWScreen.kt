package com.fredprojects.features.pws.presentation

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.pws.presentation.models.PWPModel
import com.fredprojects.features.pws.presentation.vm.UpsertPWState

@Composable
fun UpsertPWScreen(
    state: UpsertPWState,
    goBack: Action,
    upsertPW: (PWPModel) -> Unit,
    onTakePicture: () -> Uri
) {
    var pwName by rememberSaveable { mutableStateOf("") }
    var student by rememberSaveable { mutableStateOf("") }
    var variant by rememberSaveable { mutableStateOf("") }
    var lvl by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var mark by rememberSaveable { mutableStateOf("") }
    var photo by rememberSaveable { mutableStateOf("") }
    if(state.pw != null) {
        pwName = state.pw.pwName
        student = state.pw.student
        variant = state.pw.variant.toString()
        lvl = state.pw.level.toString()
        date = state.pw.date
        mark = state.pw.mark.toString()
        photo = state.pw.image
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { FredTopBar(false, goBack) },
        floatingActionButton = {
            FredFloatingActionButton(Icons.Default.Done) {
                upsertPW(PWPModel(pwName, student, variant.toIntOrNull() ?: 0, lvl.toIntOrNull() ?: 0, date, mark.toIntOrNull() ?: 0, photo, state.pw?.id))
            }
        }
    ) { paddingValues ->
        Column(Modifier.fillMaxWidth().padding(paddingValues).verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(8.dp))
            FredHeaderText(stringResource(R.string.upsert), textStyle = MaterialTheme.typography.h5)
            Spacer(Modifier.height(8.dp))
            FredTextField(pwName, { pwName = it }, R.string.enterPW)
            FredTextField(student, { student = it }, R.string.enterStudent)
            FredNumericTextField(variant, { variant = it }, R.string.enterVariant, imeAction = ImeAction.Next)
            FredNumericTextField(lvl, { lvl = it }, R.string.enterLVL, imeAction = ImeAction.Next)
            FredTextField(date, { date = it }, R.string.enterDate, keyboardType = KeyboardType.Decimal)
            FredNumericTextField(mark, { mark = it }, R.string.enterMark)
            TakePhotoButton { photo = onTakePicture().toString() }
            AsyncImage(photo.toUri(), photo, Modifier.fillMaxSize(0.5f).padding(16.dp))
        }
    }
}