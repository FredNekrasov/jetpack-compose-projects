package com.fredprojects.helloworld.ui.previews.jumps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.features.jumps.presentation.*
import com.fredprojects.features.jumps.presentation.models.JDPModel
import com.fredprojects.features.jumps.presentation.vm.JDState
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme

@Preview(
    name = "jumps",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun JumpingRopeDialogPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            JumpingRopeDialog(countOfJumps = 9) {}
        }
    }
}
@Preview(
    name = "jumps",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun JDDialogTruePreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            JDDialog(JDState(), { _ ->  }, true)
        }
    }
}
@Preview(
    name = "jumps",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun JDDialogFalsePreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            JDDialog(JDState(), { _ ->  }, false)
        }
    }
}
@Preview(
    name = "jumps",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun EmptyJDListScreenPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            JDListScreen(state = JDState(), onEvent = { _ -> }, goBack = {  }) {}
        }
    }
}
@Preview(
    name = "jumps",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun JDListScreenPreview() {
    val list = listOf(JDPModel(9))
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            JDListScreen(state = JDState(jds = list), onEvent = { _ -> }, goBack = {  }) {}
        }
    }
}