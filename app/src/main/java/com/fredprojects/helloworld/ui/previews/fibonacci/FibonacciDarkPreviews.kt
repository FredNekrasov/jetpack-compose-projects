package com.fredprojects.helloworld.ui.previews.fibonacci

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.features.fibonacci.impl.*
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme

@Preview(
    group = "fibonacci",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun FibScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            FibScreen(fibonacciSequences = emptyList(), goBack = { }) { _ -> }
        }
    }
}
@Preview(
    group = "fibonacci",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnswerFibScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            AnswerFibScreen(result = "1234567890") {}
        }
    }
}