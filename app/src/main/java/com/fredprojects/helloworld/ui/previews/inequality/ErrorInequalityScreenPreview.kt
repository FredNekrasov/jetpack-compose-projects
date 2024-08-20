package com.fredprojects.helloworld.ui.previews.inequality

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.core.ui.R
import com.fredprojects.features.inequality.impl.InequalityScreen

@Preview(
    name = "Inequality Screen",
    group = "inequality",
    apiLevel = 34,
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ErrorInequalityScreenPreview() {
    InequalityScreen(state = R.string.error) { _, _ -> }
}