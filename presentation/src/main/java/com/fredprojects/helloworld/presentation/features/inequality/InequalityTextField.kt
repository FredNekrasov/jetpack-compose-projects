package com.fredprojects.helloworld.presentation.features.inequality

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.fredprojects.helloworld.presentation.core.FredText

@Composable
internal fun InequalityTextField(
    value: String,
    onChange: (String) -> Unit,
    text: String,
    imeAction: ImeAction,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { if ((it.toFloatOrNull() != null) || it.isEmpty() || (it == "-")) onChange(it) },
        modifier = modifier,
        label = { FredText(text) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = imeAction),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.onBackground,
            backgroundColor = MaterialTheme.colors.background,
            cursorColor = MaterialTheme.colors.onBackground,
            focusedBorderColor = MaterialTheme.colors.onBackground,
            unfocusedBorderColor = MaterialTheme.colors.onBackground,
            focusedLabelColor = MaterialTheme.colors.onBackground,
            unfocusedLabelColor = MaterialTheme.colors.onBackground
        )
    )
}