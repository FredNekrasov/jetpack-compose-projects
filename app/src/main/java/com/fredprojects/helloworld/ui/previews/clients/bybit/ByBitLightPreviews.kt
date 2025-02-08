package com.fredprojects.helloworld.ui.previews.clients.bybit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.core.ui.FredIconButton
import com.fredprojects.features.clients.domain.bybit.models.BBInfo
import com.fredprojects.features.clients.domain.bybit.models.BBType
import com.fredprojects.features.clients.domain.utils.ActionStatus
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
import com.fredprojects.features.clients.presentation.bybit.FavProductList
import com.fredprojects.features.clients.presentation.bybit.ProductList
import com.fredprojects.helloworld.ui.navigation.modules.ShowConnectionInfo
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme

@Preview(
    group = "bybit",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun RealProductListNothingAndLoadingPreview() {
    HelloWorldTheme {
        Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
            CircularProgressIndicator()
        }
    }
}
@Preview(
    group = "bybit",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ProductListNothingPreview() {
    val state: ConnectionStatus<BBInfo> = ConnectionStatus.Nothing()
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background), color = MaterialTheme.colorScheme.background) {
            ProductList(list = state.list, onUpdate = { _ -> }) { ProductListNavButtons(state.status) }
        }
    }
}
@Preview(
    group = "bybit",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ProductListLoadingPreview() {
    val state: ConnectionStatus<BBInfo> = ConnectionStatus.Loading(emptyList())
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ProductList(list = state.list, onUpdate = { _ -> }) { ProductListNavButtons(state.status) }
        }
    }
}
@Preview(
    group = "bybit",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ProductListSuccessPreview() {
    val list = listOf(BBInfo("title", BBType("key", "title"), "dateTime", "description", "endDateTime", "startDateTime", listOf("tag"), "url", false, 0))
    val state: ConnectionStatus<BBInfo> = ConnectionStatus.Success(list)
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ProductList(list = state.list, onUpdate = { _ -> }) { ProductListNavButtons(state.status) }
        }
    }
}
@Preview(
    group = "bybit",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ProductListConnectionErrorPreview() {
    val state: ConnectionStatus<BBInfo> = ConnectionStatus.Error(emptyList(), ActionStatus.CONNECTION_ERROR)
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ProductList(list = state.list, onUpdate = { _ -> }) { ProductListNavButtons(state.status) }
        }
    }
}
@Preview(
    group = "bybit",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ProductListNoDataErrorPreview() {
    val state: ConnectionStatus<BBInfo> = ConnectionStatus.Error(emptyList(), ActionStatus.NO_DATA)
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ProductList(list = state.list, onUpdate = { _ -> }) { ProductListNavButtons(state.status) }
        }
    }
}
@Preview(
    group = "bybit",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ProductListNoInternetErrorPreview() {
    val state: ConnectionStatus<BBInfo> = ConnectionStatus.Error(emptyList(), ActionStatus.NO_INTERNET)
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ProductList(list = state.list, onUpdate = { _ -> }) { ProductListNavButtons(state.status) }
        }
    }
}
@Preview(
    group = "bybit",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ProductListSerializationErrorPreview() {
    val state: ConnectionStatus<BBInfo> = ConnectionStatus.Error(emptyList(), ActionStatus.SERIALIZATION_ERROR)
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ProductList(list = state.list, onUpdate = { _ -> }) { ProductListNavButtons(state.status) }
        }
    }
}
@Preview(
    group = "bybit",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ProductListUnknownPreview() {
    val state: ConnectionStatus<BBInfo> = ConnectionStatus.Error(emptyList(), ActionStatus.UNKNOWN)
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ProductList(list = state.list, onUpdate = { _ -> }) { ProductListNavButtons(state.status) }
        }
    }
}
@Composable
private fun ProductListNavButtons(status: ActionStatus) {
    Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
        FredIconButton({  }, Icons.AutoMirrored.Default.KeyboardArrowLeft, tint = MaterialTheme.colorScheme.onBackground)
        ShowConnectionInfo({  }, status)
        FredIconButton({  }, Icons.Default.Favorite, tint = MaterialTheme.colorScheme.onBackground)
    }
}
@Preview(
    group = "bybit",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun FavProductListPreview() {
    val list = listOf(
        BBInfo("title", BBType("key", "title"), "dateTime", "description", "endDateTime", "startDateTime", listOf("tag"), "url", true),
        BBInfo("title", BBType("key", "title"), "dateTime", "description", "endDateTime", "startDateTime", listOf("tag"), "url", false)
    )
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            FavProductList(list, onUpdate = { _ -> }, goBack = {})
        }
    }
}
@Preview(
    group = "bybit",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun EmptyFavProductListPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            FavProductList(emptyList(), onUpdate = { _ -> }, goBack = {})
        }
    }
}