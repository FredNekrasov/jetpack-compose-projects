package com.fredprojects.helloworld.presentation.features.inequality

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.presentation.R
import com.fredprojects.helloworld.presentation.core.FredButton
import com.fredprojects.helloworld.presentation.core.FredHeaderText

/**
 * Inequality Screen represents the screen for solving inequality in the form of ax + b < 0
 */
@Composable
fun InequalityScreen(
    state: Any,
    calculate: (Float?, Float?) -> Unit
) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        FredHeaderText("ax + b < 0", textStyle =  MaterialTheme.typography.h5)
        Spacer(Modifier.height(8.dp))
        var a by rememberSaveable { mutableStateOf("") }
        InequalityTextField(a, { a = it }, "a", ImeAction.Next)
        var b by rememberSaveable { mutableStateOf("") }
        InequalityTextField(b, { b = it }, "b", ImeAction.Done)
        Spacer(Modifier.height(8.dp))
        FredHeaderText(if(state is Int) stringResource(state) else state.toString(), MaterialTheme.typography.subtitle1)
        Spacer(Modifier.height(8.dp))
        FredButton(
            onClick = { calculate(a.toFloatOrNull(), b.toFloatOrNull()) },
            text = stringResource(R.string.displayResult)
        )
    }
}