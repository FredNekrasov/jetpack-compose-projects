package com.fredprojects.features.auth.presentation

import androidx.compose.foundation.layout.*
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
import com.fredprojects.features.auth.domain.models.User
import com.fredprojects.features.auth.presentation.vm.AuthEvents

@Composable
fun Registration(
    user: User?,
    isDataCorrect: Boolean,
    goBack: Action,
    onRegistration: (AuthEvents) -> Unit
) {
    var userName by rememberSaveable { mutableStateOf(user?.login ?: "") }
    var password by rememberSaveable { mutableStateOf(user?.password ?: "") }
    var email by rememberSaveable { mutableStateOf(user?.email ?: "") }
    var name by rememberSaveable { mutableStateOf(user?.name ?: "") }
    var surname by rememberSaveable { mutableStateOf(user?.surname ?: "") }
    Column(Modifier.fillMaxSize(),Arrangement.Center, Alignment.CenterHorizontally) {
        FredHeaderText(
            if(user == null) stringResource(R.string.registration) else stringResource(R.string.editingData),
            MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(32.dp))

        FredTextField(userName, { userName = it }, R.string.enterUN, isDataCorrect, errorString = stringResource(R.string.incorrectUserName))
        Spacer(Modifier.height(4.dp))

        PasswordTextField(password, { password = it }, isDataCorrect)
        Spacer(Modifier.height(4.dp))

        FredTextField(email, { email = it }, R.string.enterEmail, isDataCorrect, keyboardType = KeyboardType.Email, errorString = stringResource(R.string.incorrectEmail))
        Spacer(Modifier.height(4.dp))

        FredTextField(name, { name = it }, R.string.enterName)
        FredTextField(surname, { surname = it }, R.string.enterSurname, imeAction = ImeAction.Done)
        Spacer(Modifier.height(16.dp))

        FredButton(
            { onRegistration(AuthEvents.UpsertUserData(User(userName, password, email, name, surname, user?.id))) },
            if(user == null) stringResource(R.string.signUp) else stringResource(R.string.save)
        )
        Spacer(Modifier.height(4.dp))
        FredButton(goBack, stringResource(R.string.goBack))
    }
}