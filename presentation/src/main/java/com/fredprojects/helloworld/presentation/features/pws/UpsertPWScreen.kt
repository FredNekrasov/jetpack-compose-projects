package com.fredprojects.helloworld.presentation.features.pws

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
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
import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import com.fredprojects.helloworld.domain.features.pws.utils.PWStatus
import com.fredprojects.helloworld.presentation.R
import com.fredprojects.helloworld.presentation.core.*
import com.fredprojects.helloworld.presentation.features.pws.vm.UpsertPWState

@Composable
fun UpsertPWScreen(
    state: UpsertPWState,
    goBack: Action,
    upsertPW: (PracticalWork) -> Unit,
    onTakePicture: () -> Uri
) {
    var pwName by rememberSaveable { mutableStateOf("") }
    var isPWNameCorrect by rememberSaveable { mutableStateOf(true) }
    var student by rememberSaveable { mutableStateOf("") }
    var isStudentNameCorrect by rememberSaveable { mutableStateOf(true) }
    var variant by rememberSaveable { mutableStateOf("") }
    var isVariantCorrect by rememberSaveable { mutableStateOf(true) }
    var lvl by rememberSaveable { mutableStateOf("") }
    var isLVLCorrect by rememberSaveable { mutableStateOf(true) }
    var date by rememberSaveable { mutableStateOf("") }
    var isDateCorrect by rememberSaveable { mutableStateOf(true) }
    var mark by rememberSaveable { mutableStateOf("") }
    var isMarkCorrect by rememberSaveable { mutableStateOf(true) }
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
                upsertPW(PracticalWork(
                    pwName,
                    student,
                    variant.toIntOrNull() ?: 0,
                    lvl.toIntOrNull() ?: 0,
                    date,
                    mark.toIntOrNull() ?: 0,
                    photo,
                    state.pw?.id
                ))
            }
        }
    ) { paddingValues ->
        Column(Modifier.fillMaxWidth().padding(paddingValues).verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(8.dp))
            FredHeaderText(stringResource(R.string.upsert), textStyle = MaterialTheme.typography.h5)
            Spacer(Modifier.height(8.dp))
            FredTextField(pwName, { pwName = it }, R.string.enterPW, isPWNameCorrect)
            if(!isPWNameCorrect) FredText(stringResource(R.string.incorrectPW), color = MaterialTheme.colors.error)
            FredTextField(student, { student = it }, R.string.enterStudent, isStudentNameCorrect)
            if(!isStudentNameCorrect) FredText(stringResource(R.string.incorrectStudent), color = MaterialTheme.colors.error)
            FredNumericTextField(variant, { variant = it }, R.string.enterVariant, isVariantCorrect, ImeAction.Next)
            if(!isVariantCorrect) FredText(stringResource(R.string.incorrectVariant), color = MaterialTheme.colors.error)
            FredNumericTextField(lvl, { lvl = it }, R.string.enterLVL, isLVLCorrect, ImeAction.Next)
            if(!isLVLCorrect) FredText(stringResource(R.string.incorrectLVL), color = MaterialTheme.colors.error)
            FredTextField(date, { date = it }, R.string.enterDate, isDateCorrect, keyboardType = KeyboardType.Decimal)
            if(!isDateCorrect) FredHeaderText(stringResource(R.string.incorrectDate), textStyle = MaterialTheme.typography.body2, color = MaterialTheme.colors.error)
            FredNumericTextField(mark, { mark = it }, R.string.enterMark, isMarkCorrect)
            if(!isMarkCorrect) FredText(stringResource(R.string.incorrectMark), color = MaterialTheme.colors.error)
            TakePhotoButton { photo = onTakePicture().toString() }
            AsyncImage(photo.toUri(), photo, Modifier.wrapContentWidth().padding(16.dp))
        }
    }
    LaunchedEffect(key1 = true) {
        when(state.status) {
            PWStatus.SUCCESS -> goBack()
            PWStatus.INCORRECT_PW_NAME -> isPWNameCorrect = false
            PWStatus.INCORRECT_STUDENT -> isStudentNameCorrect = false
            PWStatus.INCORRECT_LEVEL -> isLVLCorrect = false
            PWStatus.INCORRECT_VARIANT -> isVariantCorrect = false
            PWStatus.INCORRECT_DATE -> isDateCorrect = false
            PWStatus.INCORRECT_MARK -> isMarkCorrect = false
            PWStatus.INCORRECT_IMAGE -> photo = onTakePicture().toString()
            PWStatus.NOTHING -> Unit
        }
    }
}