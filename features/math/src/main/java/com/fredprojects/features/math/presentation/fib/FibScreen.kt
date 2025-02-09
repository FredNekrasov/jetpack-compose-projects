package com.fredprojects.features.math.presentation.fib

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.R
import com.fredprojects.core.ui.*
import com.fredprojects.features.math.domain.utils.CalculationStatus

@Composable
fun FibScreen(fibonacciSequences: List<FibonacciBinder>, goBack: Action, calculate: (Int) -> Unit) {
    var number by rememberSaveable { mutableStateOf("") }
    var isNumberCorrect by rememberSaveable { mutableStateOf(true) }
    Scaffold(Modifier.fillMaxSize(), topBar = { FredTopBar(goBack) }) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding), Arrangement.Center, Alignment.CenterHorizontally) {
            FredNumericTextField(number, { number = it }, R.string.fibEnterNumber, isNumberCorrect)
            Spacer(Modifier.height(4.dp))
            FredButton(
                {
                    isNumberCorrect = number.isNotEmpty() && (number.toIntOrNull() != null)
                    if(isNumberCorrect) calculate(number.toInt())
                },
                stringResource(R.string.displayResult)
            )
            HorizontalDivider()
            FibSequenceList(fibonacciSequences)
        }
    }
}
@Composable
private fun FibSequenceList(fibonacciSequences: List<FibonacciBinder>) {
    LazyColumn {
        items(fibonacciSequences, key = { it.hashCode() }) {
            Spacer(Modifier.height(4.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.onBackground)
            val result = it.result.collectAsState().value
            val text = when(result.first) {
                CalculationStatus.SUCCESS -> result.second.toString()
                CalculationStatus.INIT -> stringResource(R.string.fibCalculationsBegun)
                CalculationStatus.ERROR -> stringResource(R.string.fibCalculationsCanceled)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                FredText(text, Modifier.padding(horizontal = 8.dp))
                if (result.first == CalculationStatus.INIT) FredButton({ it.cancel() }, stringResource(R.string.cancel))
            }
        }
    }
}