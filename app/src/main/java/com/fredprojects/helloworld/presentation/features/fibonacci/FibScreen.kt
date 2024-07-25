package com.fredprojects.helloworld.presentation.features.fibonacci

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.domain.features.fibonacci.utils.CalculationStatus
import com.fredprojects.helloworld.presentation.core.*

@Composable
fun FibScreen(fibonacciSequences: List<FibonacciBinder>, calculate: (String) -> Unit) {
    var number by rememberSaveable { mutableStateOf("") }
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        FredNumericTextField(number, { number = it }, R.string.fibEnterNumber)
        FredButton({ calculate(number) }, stringResource(R.string.displayResult))
        FibSequenceList(fibonacciSequences)
    }
}
@Composable
private fun FibSequenceList(fibonacciSequences: List<FibonacciBinder>) {
    LazyColumn {
        items(fibonacciSequences) {
            val result = it.result.collectAsState().value
            val text = when(result.first) {
                CalculationStatus.SUCCESS -> result.second.toString()
                CalculationStatus.INIT -> stringResource(R.string.fibCalculationsBegun)
                CalculationStatus.ERROR -> stringResource(R.string.fibCalculationsCanceled)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                FredText(text, Modifier.padding(start = 8.dp, end = 8.dp))
                if (result.first == CalculationStatus.INIT) FredButton({ it.cancel() }, stringResource(R.string.cancel))
            }
        }
    }
}