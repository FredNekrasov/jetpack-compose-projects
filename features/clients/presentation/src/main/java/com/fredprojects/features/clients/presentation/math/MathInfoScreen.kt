package com.fredprojects.features.clients.presentation.math

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.R
import com.fredprojects.core.ui.*
import com.fredprojects.features.clients.domain.math.models.MathModel
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
import com.fredprojects.features.clients.presentation.getString

@Composable
fun MathInfoScreen(
    state: ConnectionStatus<MathModel>,
    onSearch: (String) -> Unit
) {
    var expression by rememberSaveable { mutableStateOf("") }
    var isExpressionCorrect by rememberSaveable { mutableStateOf(true) }
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        FredTextField(expression, { expression = it }, R.string.enterExpression, isExpressionCorrect, ImeAction.Done)
        if(!isExpressionCorrect) FredText(stringResource(R.string.error), color = MaterialTheme.colors.error)
        Spacer(Modifier.height(4.dp))
        FredButton(
            {
                isExpressionCorrect = expression.isNotEmpty()
                if(isExpressionCorrect) onSearch(expression)
            },
            stringResource(R.string.search)
        )
        FredText(stringResource(state.status.getString()))
        if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) MathScreenContent(state.list) else MathScreenContent(state.list, 2)
    }
}
@Composable
private fun MathScreenContent(mathInfoList: List<MathModel>, gridColumns: Int = 1) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(gridColumns),
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(mathInfoList) {
            Spacer(Modifier.height(4.dp))
            MathInfoListItem(it, Modifier.wrapContentSize())
        }
    }
}