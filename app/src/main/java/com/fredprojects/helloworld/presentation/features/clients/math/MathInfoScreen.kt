package com.fredprojects.helloworld.presentation.features.clients.math

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.domain.core.utils.ConnectionStatus
import com.fredprojects.helloworld.domain.features.clients.common.MathModel
import com.fredprojects.helloworld.presentation.core.*

@Composable
fun MathInfoScreen(
    state: ConnectionStatus<MathModel>,
    onSearch: (String) -> Unit
) {
    var expression by rememberSaveable { mutableStateOf("") }
    var isExpressionCorrect by rememberSaveable { mutableStateOf(true) }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()), Arrangement.Center, Alignment.CenterHorizontally) {
        Spacer(Modifier.height(8.dp))
        FredTextField(expression, { expression = it }, R.string.enterExpression, R.string.error, isExpressionCorrect, ImeAction.Done)
        Spacer(Modifier.height(4.dp))
        FredButton(
            {
                isExpressionCorrect = expression.isNotEmpty()
                if(isExpressionCorrect) onSearch(expression)
            },
            stringResource(R.string.search)
        )
        Spacer(Modifier.height(4.dp))
        FredText(stringResource(state.status.getString()))
        if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) MathScreenContent(2, state.list) else MathScreenContent(3, state.list)
    }
}
@Composable
private fun MathScreenContent(gridColumns: Int, mathInfoList: List<MathModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(gridColumns),
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(mathInfoList) {
            MathInfoListItem(it, Modifier.wrapContentSize())
        }
    }
}