package com.fredprojects.helloworld.ui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.features.fibonacci.impl.AnswerFibScreen
import com.fredprojects.features.fibonacci.impl.FibScreen

@Preview(
    group = "fibonacci",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun FibScreenPreview() {
    FibScreen(fibonacciSequences = emptyList(), goBack = { }) { _ -> }
}
@Preview(
    group = "fibonacci",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AnswerFibScreenPreview() {
    AnswerFibScreen(result = "1234567890") {}
}