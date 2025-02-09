package com.fredprojects.features.auth.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.R
import com.fredprojects.core.ui.*
import com.fredprojects.features.auth.presentation.vm.AuthEvents

@Composable
fun Authorization(
    isDataCorrect: Boolean,
    onAuth: (AuthEvents) -> Unit,
    toRegistration: Action
) {
    var userName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        FredHeaderText(text = stringResource(R.string.authorization), textStyle = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(32.dp))
        FredTextField(userName, { userName = it }, R.string.enterUN, isDataCorrect, errorString = stringResource(R.string.incorrectUserName))
        Spacer(Modifier.height(4.dp))
        PasswordTextField(password, { password = it }, isDataCorrect, ImeAction.Done)
        Spacer(Modifier.height(16.dp))
        FredButton({ onAuth(AuthEvents.Authorization(userName, password)) }, stringResource(R.string.logIn))
        Spacer(Modifier.height(8.dp))
        FredButton(toRegistration, stringResource(R.string.registration))
    }
}