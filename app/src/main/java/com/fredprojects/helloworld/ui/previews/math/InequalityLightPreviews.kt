package com.fredprojects.helloworld.ui.previews.math

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.core.ui.R
import com.fredprojects.features.math.presentation.inequality.InequalityScreen
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme

@Preview(
    group = "inequality",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ErrorInequalityScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            InequalityScreen(state = R.string.error) { _, _ -> }
        }
    }
}
@Preview(
    group = "inequality",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun InequalityHasNoSolutionsPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            InequalityScreen(state = R.string.inequalityHasNoSolutions) { _, _ -> }
        }
    }
}
@Preview(
    group = "inequality",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ResultInequalityScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            InequalityScreen(state = "Result") { _, _ -> }
        }
    }
}