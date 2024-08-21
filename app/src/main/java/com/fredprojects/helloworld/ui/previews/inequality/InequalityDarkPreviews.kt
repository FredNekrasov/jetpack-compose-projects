package com.fredprojects.helloworld.ui.previews.inequality

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.core.ui.R
import com.fredprojects.features.inequality.impl.InequalityScreen
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme

@Preview(
    group = "inequality",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ErrorInequalityScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            InequalityScreen(state = R.string.error) { _, _ -> }
        }
    }
}
@Preview(
    group = "inequality",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun InequalityHasNoSolutionsPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            InequalityScreen(state = R.string.inequalityHasNoSolutions) { _, _ -> }
        }
    }
}
@Preview(
    group = "inequality",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ResultInequalityScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            InequalityScreen(state = "Result") { _, _ -> }
        }
    }
}