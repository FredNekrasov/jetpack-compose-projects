package com.fredprojects.features.auth.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.auth.presentation.models.UDPModel
import com.fredprojects.features.auth.presentation.vm.AuthEvents

@Composable
fun Registration(
    isDataCorrect: Boolean,
    onRegistration: (AuthEvents) -> Unit,
    goBack: Action,
    user: UDPModel? = null,
) {
    var userName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var surname by rememberSaveable { mutableStateOf("") }
    user?.let {
        userName = it.login
        password = it.password
        email = it.email
        name = it.name
        surname = it.surname
    }
    Column(Modifier.fillMaxSize(),Arrangement.Center, Alignment.CenterHorizontally) {
        FredHeaderText(
            if(user == null) stringResource(R.string.registration) else stringResource(R.string.editingData),
            MaterialTheme.typography.h5
        )
        Spacer(Modifier.height(32.dp))
        FredTextField(userName, { userName = it }, R.string.enterUN, isDataCorrect)
        if(!isDataCorrect) FredText(stringResource(R.string.incorrectUserName), color = MaterialTheme.colors.error)
        Spacer(Modifier.height(4.dp))
        PasswordTextField(value = password, onChange = { password = it }, isValueCorrect = isDataCorrect)
        if(!isDataCorrect) FredText(stringResource(R.string.incorrectPassword), color = MaterialTheme.colors.error)
        Spacer(Modifier.height(4.dp))
        FredTextField(email, { email = it }, R.string.enterEmail, isDataCorrect, keyboardType = KeyboardType.Email)
        if(!isDataCorrect) FredText(stringResource(R.string.incorrectEmail), color = MaterialTheme.colors.error)
        Spacer(Modifier.height(4.dp))
        FredTextField(name, { name = it }, R.string.enterName)
        Spacer(Modifier.height(4.dp))
        FredTextField(surname, { surname = it }, R.string.enterSurname)
        Spacer(Modifier.height(16.dp))
        FredButton(
            { onRegistration(AuthEvents.UpsertUserData(UDPModel(userName, password, email, name, surname, user?.id))) },
            if(user == null) stringResource(R.string.signUp) else stringResource(R.string.save)
        )
        Spacer(Modifier.height(4.dp))
        FredButton(goBack, stringResource(R.string.goBack))
    }
}