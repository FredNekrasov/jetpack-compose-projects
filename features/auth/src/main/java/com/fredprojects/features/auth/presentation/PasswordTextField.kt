package com.fredprojects.features.auth.presentation

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import com.fredprojects.core.ui.FredText
import com.fredprojects.core.ui.R

@Composable
internal fun PasswordTextField(
    value: String,
    onChange: (String) -> Unit,
    isValueCorrect: Boolean = true,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { FredText(stringResource(R.string.enterPassword)) },
        isError = !isValueCorrect,
        visualTransformation = PasswordVisualTransformation('*'),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = imeAction),
        supportingText = { if (!isValueCorrect) FredText(stringResource(R.string.incorrectPassword)) },
        colors = OutlinedTextFieldDefaults.colors()
    )
}