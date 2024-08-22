package com.fredprojects.helloworld.ui.navigation.modules

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fredprojects.core.ui.*
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
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                    FredIconButton(controller::navigateUp, Icons.AutoMirrored.Default.KeyboardArrowLeft, tint = MaterialTheme.colors.onBackground)
                    ShowConnectionInfo(bbVM::getData, state.status)
                    FredIconButton({ controller.navigate(Routes.FAV_PRODUCTS) }, Icons.Default.Favorite, tint = MaterialTheme.colors.onBackground)
                }
            }
        } else Box(Modifier.fillMaxSize()) { CircularProgressIndicator(Modifier.align(Alignment.Center), color = MaterialTheme.colors.onBackground) }
    }
    composable(Routes.FAV_PRODUCTS) {
        FavProductList(bbVM.bbState.collectAsState().value.list, bbVM::update, controller::navigateUp)
    }
}
@Composable
internal fun ShowConnectionInfo(getData: () -> Unit, status: ActionStatus) {
    val tryAgain = stringResource(R.string.tryAgain)
    TextButton(getData, enabled = status != ActionStatus.SUCCESS) {
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