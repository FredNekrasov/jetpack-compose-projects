package com.fredprojects.helloworld.ui.navigation.modules

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fredprojects.core.ui.FredIconButton
import com.fredprojects.core.ui.R
import com.fredprojects.features.auth.domain.utils.AuthStatus
import com.fredprojects.features.auth.presentation.*
import com.fredprojects.features.auth.presentation.vm.UserVM
import com.fredprojects.helloworld.MainActivity.Companion.DISPLAY_RESULT
import com.fredprojects.helloworld.ui.displayToast
import com.fredprojects.helloworld.ui.navigation.Routes
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
                userVM.authStatusSF.collectLatest {
                    when(it) {
                        AuthStatus.SUCCESS -> {
                            isDataCorrect = true
                            controller.navigate(Routes.PROFILE)
                        }
                        AuthStatus.INVALID_USERNAME -> {
                            activityContext.displayToast(R.string.incorrectUserName)
                            isDataCorrect = false
                        }
                        AuthStatus.INVALID_PASSWORD -> {
                            activityContext.displayToast(R.string.incorrectPassword)
                            isDataCorrect = false
                        }
                        AuthStatus.INVALID_DATA -> {
                            activityContext.displayToast(R.string.error)
                            isDataCorrect = false
                        }
                        else -> isDataCorrect = true
                    }
                }
            }
        } else controller.navigate(Routes.RESULT_FIB)
    }
    composable(Routes.REGISTRATION) {
        var isDataCorrect by rememberSaveable { mutableStateOf(true) }
        Registration(isDataCorrect, userVM::onEvent, controller::navigateUp, userVM.udpModel)
        LaunchedEffect(true) {
            userVM.authStatusSF.collectLatest {
                when(it) {
                    AuthStatus.SUCCESS -> {
                        isDataCorrect = true
                        controller.navigate(Routes.PROFILE)
                    }
                    AuthStatus.INVALID_USERNAME -> {
                        activityContext.displayToast(R.string.incorrectUserName)
                        isDataCorrect = false
                    }
                    AuthStatus.INVALID_PASSWORD -> {
                        activityContext.displayToast(R.string.incorrectPassword)
                        isDataCorrect = false
                    }
                    AuthStatus.INVALID_EMAIL -> {
                        activityContext.displayToast(R.string.incorrectEmail)
                        isDataCorrect = false
                    }
                    AuthStatus.INVALID_DATA -> {
                        activityContext.displayToast(R.string.error)
                        isDataCorrect = false
                    }
                    AuthStatus.EXISTING_DATA -> {
                        activityContext.displayToast(R.string.existingUser)
                        isDataCorrect = false
                    }
                    AuthStatus.NOTHING -> isDataCorrect = true
                }
            }
        }
    }
    composable(Routes.PROFILE) {
        Profile(userVM.udpModel, userVM::onEvent, { controller.navigate(Routes.REGISTRATION) }) {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                FredIconButton({ controller.navigate(Routes.BYBIT) }, Icons.Outlined.ShoppingCart)
                FredIconButton({ controller.navigate(Routes.FAV_PRODUCTS) }, Icons.Outlined.Favorite)
            }
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                FredIconButton({ controller.navigate(Routes.MAIN_SCREEN) }, Icons.Outlined.Home)
                FredIconButton({ controller.navigate(Routes.FIBONACCI) }, Icons.AutoMirrored.Outlined.Send)
                FredIconButton({ controller.navigate(Routes.PW_LIST) }, Icons.Outlined.Star)
                FredIconButton({ controller.navigate(Routes.JD_LIST) }, Icons.Outlined.DateRange)
            }
        }
    }
}