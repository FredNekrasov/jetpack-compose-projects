package com.fredprojects.helloworld.ui.previews.clients.astronomy

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.features.clients.domain.astronomy.models.AstronomyInfo
import com.fredprojects.features.clients.domain.utils.*
import com.fredprojects.features.clients.presentation.astronomy.AstronomyInfoScreen
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme

@Preview(
    group = "astronomyInfo",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AstronomyInfoSuccessScreenPreview() {
    val list = listOf(AstronomyInfo("11:11:12", "Proxima", "qwerty", "11:11:11", 2f))
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AstronomyInfoScreen(state = ConnectionStatus.Success(list), onSearch = { _, _, _ -> })
        }
    }
}
@Preview(
    group = "astronomyInfo",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AstronomyInfoNothingScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AstronomyInfoScreen(state = ConnectionStatus.Nothing(), onSearch = { _, _, _ -> })
        }
    }
}
@Preview(
    group = "astronomyInfo",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AstronomyInfoLoadingScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AstronomyInfoScreen(state = ConnectionStatus.Loading(emptyList()), onSearch = { _, _, _ -> })
        }
    }
}
@Preview(
    group = "astronomyInfo",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AstronomyInfoNoDataErrorScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AstronomyInfoScreen(
                state = ConnectionStatus.Error(emptyList(), ActionStatus.NO_DATA),
                onSearch = { _, _, _ -> }
            )
        }
    }
}
@Preview(
    group = "astronomyInfo",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AstronomyInfoConnectionErrorScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AstronomyInfoScreen(
                state = ConnectionStatus.Error(emptyList(), ActionStatus.CONNECTION_ERROR),
                onSearch = { _, _, _ -> }
            )
        }
    }
}
@Preview(
    group = "astronomyInfo",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AstronomyInfoNoInternetErrorScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AstronomyInfoScreen(
                state = ConnectionStatus.Error(emptyList(), ActionStatus.NO_INTERNET),
                onSearch = { _, _, _ -> }
            )
        }
    }
}
@Preview(
    group = "astronomyInfo",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AstronomyInfoSerializationErrorScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AstronomyInfoScreen(
                state = ConnectionStatus.Error(emptyList(), ActionStatus.SERIALIZATION_ERROR),
                onSearch = { _, _, _ -> }
            )
        }
    }
}
@Preview(
    group = "astronomyInfo",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AstronomyInfoUnknownErrorScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AstronomyInfoScreen(
                state = ConnectionStatus.Error(emptyList(), ActionStatus.UNKNOWN),
                onSearch = { _, _, _ -> }
            )
        }
    }
}