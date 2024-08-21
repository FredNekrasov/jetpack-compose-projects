package com.fredprojects.helloworld.ui.previews.clients.math

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.features.clients.domain.math.models.MathModel
import com.fredprojects.features.clients.domain.utils.*
import com.fredprojects.features.clients.presentation.math.MathInfoScreen
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme

@Preview(
    group = "math",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun MathInfoNothingScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            MathInfoScreen(state = ConnectionStatus.Nothing()) { _ -> }
        }
    }
}
@Preview(
    group = "math",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun MathInfoLoadingScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            MathInfoScreen(state = ConnectionStatus.Loading(emptyList())) { _ -> }
        }
    }
}
@Preview(
    group = "math",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun MathInfoSuccessScreenPreview() {
    val list = listOf(MathModel("1 + 1", "2"))
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            MathInfoScreen(state = ConnectionStatus.Success(list)) { _ -> }
        }
    }
}
@Preview(
    group = "math",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun MathInfoConnectionErrorScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            MathInfoScreen(
                state = ConnectionStatus.Error(emptyList(), ActionStatus.CONNECTION_ERROR)
            ) { _ -> }
        }
    }
}
@Preview(
    group = "math",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun MathInfoNoInternetErrorScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            MathInfoScreen(
                state = ConnectionStatus.Error(emptyList(), ActionStatus.NO_INTERNET)
            ) { _ -> }
        }
    }
}
@Preview(
    group = "math",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun MathInfoNoDataErrorScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            MathInfoScreen(
                state = ConnectionStatus.Error(emptyList(), ActionStatus.NO_DATA)
            ) { _ -> }
        }
    }
}
@Preview(
    group = "math",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun MathInfoSerializationErrorScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            MathInfoScreen(
                state = ConnectionStatus.Error(emptyList(), ActionStatus.SERIALIZATION_ERROR)
            ) { _ -> }
        }
    }
}
@Preview(
    group = "math",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun MathInfoUnknownErrorScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            MathInfoScreen(
                state = ConnectionStatus.Error(emptyList(), ActionStatus.UNKNOWN)
            ) { _ -> }
        }
    }
}