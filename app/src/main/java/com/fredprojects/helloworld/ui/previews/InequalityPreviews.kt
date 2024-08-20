package com.fredprojects.helloworld.ui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fredprojects.core.ui.R
import com.fredprojects.features.inequality.impl.InequalityScreen

@Preview(
    group = "inequality",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ErrorInequalityScreenPreview() {
    InequalityScreen(state = R.string.error) { _, _ -> }
}
@Preview(
    group = "inequality",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun InequalityHasNoSolutionsPreview() {
    InequalityScreen(state = R.string.inequalityHasNoSolutions) { _, _ -> }
}
@Preview(
    group = "inequality",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ResultInequalityScreenPreview() {
    InequalityScreen(state = "Result") { _, _ -> }
}