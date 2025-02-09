package com.fredprojects.features.math.presentation.inequality

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R

/**
 * Inequality Screen represents the screen for solving inequality in the form of ax + b < 0
 */
@Composable
fun InequalityScreen(
    result: Any,
    calculate: (Float?, Float?) -> Unit
) {
    var a by rememberSaveable { mutableStateOf("") }
    var b by rememberSaveable { mutableStateOf("") }
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        FredHeaderText("ax + b < 0", textStyle =  MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        InequalityTextField(a, { a = it }, "a", ImeAction.Next)
        InequalityTextField(b, { b = it }, "b", ImeAction.Done)
        Spacer(Modifier.height(8.dp))
        FredHeaderText(if(result is Int) stringResource(result) else result.toString(), MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(8.dp))
        FredButton(
            onClick = { calculate(a.toFloatOrNull(), b.toFloatOrNull()) },
            text = stringResource(R.string.displayResult)
        )
    }
}