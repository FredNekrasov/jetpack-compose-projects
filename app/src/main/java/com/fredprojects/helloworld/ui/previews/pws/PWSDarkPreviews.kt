package com.fredprojects.helloworld.ui.previews.pws

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.presentation.*
import com.fredprojects.features.pws.presentation.vm.PWState
import com.fredprojects.features.pws.presentation.vm.UpsertPWState
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme

@Preview(
    group = "pws",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PWEmptyListScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            PWListScreen(PWState(), onEvent = { _ ->  }, goBack = {  }) { _ ->  }
        }
    }
}
@Preview(
    group = "pws",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PWListScreenPreview() {
    val list = listOf(
        PracticalWork("oop", "fred", 1, 9, "11.11.2020", 5, "https://img.izismile.com/img/img2/20090722/bonus/4/big_cats_18.jpg"),
        PracticalWork("oop", "alex", 1, 9, "11.11.2020", 5, "https://img.izismile.com/img/img2/20090722/bonus/4/big_cats_18.jpg")
    )
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            PWListScreen(PWState(pws = list), onEvent = { _ ->  }, goBack = {  }) { _ ->  }
        }
    }
}
@Preview(
    group = "pws",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun UpsertPWScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            UpsertPWScreen(UpsertPWState(), goBack = {  }, upsertPW = { _ ->  }) { Uri.EMPTY }
        }
    }
}