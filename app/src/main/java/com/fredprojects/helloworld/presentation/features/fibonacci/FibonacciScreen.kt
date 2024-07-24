package com.fredprojects.helloworld.presentation.features.fibonacci

import androidx.compose.runtime.Composable
import com.fredprojects.helloworld.presentation.core.Action
import com.fredprojects.helloworld.presentation.features.fibonacci.binder.FibonacciBinder

@Composable
fun FibonacciScreen(
    condition: Boolean,
    result: String,
    calculate: (String) -> Unit,
    fibonacciSequences: List<FibonacciBinder>,
    cancel: Action
) {
    if (condition) AnswerFibScreen(result) else FibScreen(calculate, fibonacciSequences, cancel)
}