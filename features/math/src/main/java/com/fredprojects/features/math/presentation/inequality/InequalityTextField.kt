package com.fredprojects.features.math.presentation.inequality

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.fredprojects.core.ui.FredText

@Composable
internal fun InequalityTextField(
    value: String,
    onChange: (String) -> Unit,
    label: String,
    imeAction: ImeAction,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { if ((it.toFloatOrNull() != null) || it.isEmpty() || (it == "-")) onChange(it) },
        modifier = modifier,
        label = { FredText(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = imeAction)
    )
}