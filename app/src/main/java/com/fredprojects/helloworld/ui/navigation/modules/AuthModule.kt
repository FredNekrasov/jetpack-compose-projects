package com.fredprojects.helloworld.ui.navigation.modules

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fredprojects.core.ui.FredIconButton
import com.fredprojects.features.auth.presentation.*
import com.fredprojects.features.auth.presentation.vm.UserVM
import com.fredprojects.helloworld.MainActivity.Companion.DISPLAY_RESULT
import com.fredprojects.helloworld.ui.navigation.Routes
import com.fredprojects.helloworld.ui.onStatus
import kotlinx.coroutines.flow.collectLatest

fun NavGraphBuilder.authModule(
    userVM: UserVM,
    activityContext: ComponentActivity,
    controller: NavHostController,
) {
    composable(Routes.AUTH) {
        if(activityContext.intent.action != DISPLAY_RESULT) {
            var isDataCorrect by rememberSaveable { mutableStateOf(true) }
            Authorization(isDataCorrect, userVM::onEvent) { controller.navigate(Routes.REGISTRATION) }
            LaunchedEffect(true) {
                userVM.authStatusSF.collectLatest { authStatus ->
                    authStatus.onStatus(onError = { isDataCorrect = it }, context = activityContext) {
                        isDataCorrect = true
                        controller.navigate(Routes.PROFILE)
                    }
                }
            }
        } else controller.navigate(Routes.RESULT_FIB)
    }
    composable(Routes.REGISTRATION) {
        var isDataCorrect by rememberSaveable { mutableStateOf(true) }
        Registration(userVM.authState, isDataCorrect, controller::navigateUp, userVM::onEvent)
        LaunchedEffect(true) {
            userVM.authStatusSF.collectLatest { authStatus ->
                authStatus.onStatus(onError = { isDataCorrect = it }, context = activityContext) {
                    isDataCorrect = true
                    controller.navigate(Routes.PROFILE)
                }
            }
        }
    }
    composable(Routes.PROFILE) {
        Profile(
            userVM.authState,
            { userVM.onEvent(it); controller.popBackStack(Routes.AUTH, false) },
            { controller.navigate(Routes.REGISTRATION) }
        ) {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                FredIconButton({ controller.navigate(Routes.BYBIT) }, Icons.Default.ShoppingCart, tint = MaterialTheme.colors.onBackground)
                FredIconButton({ controller.navigate(Routes.FAV_PRODUCTS) }, Icons.Default.Favorite, tint = MaterialTheme.colors.onBackground)
            }
            Spacer(Modifier.height(4.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                FredIconButton({ controller.navigate(Routes.MAIN_SCREEN) }, Icons.Default.Home, tint = MaterialTheme.colors.onBackground)
                FredIconButton({ controller.navigate(Routes.FIBONACCI) }, Icons.AutoMirrored.Default.Send, tint = MaterialTheme.colors.onBackground)
                FredIconButton({ controller.navigate(Routes.PW_LIST) }, Icons.Default.Star, tint = MaterialTheme.colors.onBackground)
                FredIconButton({ controller.navigate(Routes.JD_LIST) }, Icons.Default.DateRange, tint = MaterialTheme.colors.onBackground)
            }
        }
    }
}