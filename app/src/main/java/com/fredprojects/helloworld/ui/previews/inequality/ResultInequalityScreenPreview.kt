package com.fredprojects.helloworld.ui.previews.inequality

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.features.inequality.impl.InequalityScreen

@Preview(
    name = "Inequality Screen",
    group = "inequality",
    apiLevel = 34,
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ResultInequalityScreenPreview() {
    InequalityScreen(state = "Result") { _, _ -> }
}