package com.fredprojects.helloworld.presentation.features.pws.portrait

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import com.fredprojects.helloworld.domain.features.pws.utils.PWStatus
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.presentation.core.*
import com.fredprojects.helloworld.presentation.features.pws.TakePhotoButton
import com.fredprojects.helloworld.presentation.features.pws.vm.UpsertPWState

@Composable
fun PortraitUpsertPWScreen(
    state: UpsertPWState,
    goBack: Action,
    onTakePicture: () -> Uri,
    onUpsert: (PracticalWork) -> Unit
) {
    var pwName by rememberSaveable { mutableStateOf(state.pw?.pwName ?: "") }
    var isPWNameCorrect by rememberSaveable { mutableStateOf(true) }
    var student by rememberSaveable { mutableStateOf(state.pw?.student ?: "") }
    var isStudentNameCorrect by rememberSaveable { mutableStateOf(true) }
    var variant by rememberSaveable { mutableStateOf("${state.pw?.variant ?: ""}") }
    var isVariantCorrect by rememberSaveable { mutableStateOf(true) }
    var lvl by rememberSaveable { mutableStateOf("${state.pw?.level ?: ""}") }
    var isLVLCorrect by rememberSaveable { mutableStateOf(true) }
    var date by rememberSaveable { mutableStateOf(state.pw?.date ?: "") }
    var isDateCorrect by rememberSaveable { mutableStateOf(true) }
    var mark by rememberSaveable { mutableStateOf("${state.pw?.mark ?: ""}") }
    var isMarkCorrect by rememberSaveable { mutableStateOf(true) }
    var photo by rememberSaveable { mutableStateOf(state.pw?.image ?: "") }
    Scaffold(
        floatingActionButton = {
            FredFloatingActionButton(
                onClick = {
                    onUpsert(PracticalWork(pwName, student, variant.toIntOrNull() ?: 0, lvl.toIntOrNull() ?: 0, date, mark.toIntOrNull() ?: 0, photo))
                    when(state.status) {
                        PWStatus.SUCCESS -> goBack()
                        PWStatus.INCORRECT_PW_NAME -> isPWNameCorrect = false
                        PWStatus.INCORRECT_STUDENT -> isStudentNameCorrect = false
                        PWStatus.INCORRECT_LEVEL -> isLVLCorrect = false
                        PWStatus.INCORRECT_VARIANT -> isVariantCorrect = false
                        PWStatus.INCORRECT_DATE -> isDateCorrect = false
                        PWStatus.INCORRECT_MARK -> isMarkCorrect = false
                        PWStatus.INCORRECT_IMAGE -> photo = onTakePicture().toString()
                    }
                },
                icon = Icons.Default.Add
            )
        }
    ) { paddingValues ->
        Column(Modifier.fillMaxWidth().padding(paddingValues), Arrangement.Center, Alignment.CenterHorizontally) {
            Spacer(Modifier.height(16.dp))
            FredHeaderText("${stringResource(R.string.add)}/${stringResource(R.string.update)}", textStyle = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))
            FredTextField(pwName, { pwName = it }, R.string.enterPW, R.string.incorrectPW, isPWNameCorrect)
            Spacer(Modifier.height(8.dp))
            FredTextField(student, { student = it }, R.string.enterStudent, R.string.incorrectStudent, isStudentNameCorrect)
            Spacer(Modifier.height(8.dp))
            FredNumericTextField(variant, { variant = it }, R.string.enterVariant, R.string.incorrectVariant, isVariantCorrect, ImeAction.Next)
            Spacer(Modifier.height(8.dp))
            FredNumericTextField(lvl, { lvl = it }, R.string.enterLVL, R.string.incorrectLVL, isLVLCorrect, ImeAction.Next)
            Spacer(Modifier.height(8.dp))
            FredTextField(date, { date = it }, R.string.enterDate, R.string.incorrectDate, isDateCorrect)
            Spacer(Modifier.height(8.dp))
            FredNumericTextField(mark, { mark = it }, R.string.enterMark, R.string.incorrectMark, isMarkCorrect)
            Image(rememberAsyncImagePainter(photo.toUri()), photo, Modifier.wrapContentSize().padding(8.dp))
            TakePhotoButton { photo = onTakePicture().toString() }
        }
    }
}