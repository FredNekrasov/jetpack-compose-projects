package com.fredprojects.helloworld.ui.navigation.modules

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fredprojects.core.ui.FredButton
import com.fredprojects.core.ui.R
import com.fredprojects.features.clients.domain.utils.ActionStatus
import com.fredprojects.features.clients.presentation.bybit.*
import com.fredprojects.helloworld.ui.isLoading
import com.fredprojects.helloworld.ui.navigation.Routes

fun NavGraphBuilder.byBitScreens(bbVM: ByBitVM, controller: NavHostController) {
    composable(Routes.BYBIT) {
        val state = bbVM.bbState.collectAsState().value
        if(!state.status.isLoading()) {
            ProductList(list = state.list, onUpdate = bbVM::update) {
                FredButton({ controller.navigateUp() }, stringResource(R.string.goBack))
                ShowConnectionInfo(bbVM::getData, state.status)
                FredButton({ controller.navigate(Routes.FAV_PRODUCTS) }, stringResource(R.string.favourites))
            }
        } else CircularProgressIndicator(color = MaterialTheme.colors.onBackground)
    }
    composable(Routes.FAV_PRODUCTS) {
        FavProductList(bbVM.bbState.collectAsState().value.list, bbVM::update, controller::navigateUp)
    }
}
@Composable
private fun ShowConnectionInfo(getData: () -> Unit, status: ActionStatus) {
    val tryAgain = stringResource(R.string.tryAgain)
    TextButton(getData, enabled = status == ActionStatus.SUCCESS) {
        Text(
            when(status) {
                ActionStatus.SUCCESS -> stringResource(R.string.success)
                ActionStatus.NO_DATA -> "${stringResource(R.string.noData)}\n${tryAgain}"
                ActionStatus.CONNECTION_ERROR -> "${stringResource(R.string.connectionError)}\n${tryAgain}"
                ActionStatus.NO_INTERNET -> "${stringResource(R.string.noInternet)}\n${tryAgain}"
                else -> "${stringResource(R.string.unknownError)}\n${tryAgain}"
            },
            textAlign = TextAlign.Center
        )
    }
}