package com.fredprojects.helloworld.ui.previews.math

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.features.math.presentation.fib.*
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme

@Preview(
    group = "fibonacci",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun FibScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            FibScreen(fibonacciSequences = emptyList(), goBack = { }) { _ -> }
        }
    }
}
@Preview(
    group = "fibonacci",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun AnswerFibScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AnswerFibScreen(result = "1234567890") {}
        }
    }
}