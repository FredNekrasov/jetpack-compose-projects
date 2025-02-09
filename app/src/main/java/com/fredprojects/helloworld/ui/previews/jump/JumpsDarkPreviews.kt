package com.fredprojects.helloworld.ui.previews.jump

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.features.jump.domain.models.JumpData
import com.fredprojects.features.jump.presentation.jump.*
import com.fredprojects.features.jump.presentation.menu.*
import com.fredprojects.features.jump.presentation.menu.vm.JDState
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
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
    val list = listOf(JumpData(9))
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            JDListScreen(state = JDState(jds = list), onEvent = { _ -> }, goBack = {  }) {}
        }
    }
}