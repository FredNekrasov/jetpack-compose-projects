package com.fredprojects.helloworld.ui.navigation.modules

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.fredprojects.core.ui.R
import com.fredprojects.features.pws.domain.utils.PWStatus
import com.fredprojects.features.pws.presentation.PWListScreen
import com.fredprojects.features.pws.presentation.UpsertPWScreen
import com.fredprojects.features.pws.presentation.vm.PWListVM
import com.fredprojects.features.pws.presentation.vm.UpsertPWVM
import com.fredprojects.helloworld.ui.displayToast
import com.fredprojects.helloworld.ui.navigation.Routes
import kotlinx.coroutines.flow.collectLatest

fun NavGraphBuilder.pwsModule(
    pwListVM: PWListVM,
    upsertPWVM: UpsertPWVM,
    activityContext: ComponentActivity,
    controller: NavHostController
) {
    composable(Routes.UPSERT_PW + "?id={id}", listOf(navArgument("id") { type = NavType.IntType; defaultValue = -1 })) {
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            if(!it) activityContext.displayToast(R.string.error)
        }
        UpsertPWScreen(upsertPWVM.upsertPWState.collectAsState().value, controller::navigateUp, upsertPWVM::upsert) {
            upsertPWVM.takePicture(launcher::launch)
        }
        LaunchedEffect(key1 = true) {
            upsertPWVM.upsertPWState.collectLatest {
                when(it.status) {
                    PWStatus.SUCCESS -> controller.navigateUp()
                    PWStatus.INCORRECT_PW_NAME -> activityContext.displayToast(R.string.incorrectPW)
                    PWStatus.INCORRECT_STUDENT -> activityContext.displayToast(R.string.incorrectStudent)
                    PWStatus.INCORRECT_LEVEL -> activityContext.displayToast(R.string.incorrectLVL)
                    PWStatus.INCORRECT_VARIANT -> activityContext.displayToast(R.string.incorrectVariant)
                    PWStatus.INCORRECT_DATE -> activityContext.displayToast(R.string.incorrectDate)
                    PWStatus.INCORRECT_MARK -> activityContext.displayToast(R.string.incorrectMark)
                    PWStatus.INCORRECT_IMAGE -> activityContext.displayToast(R.string.takePicture)
                    PWStatus.NOTHING -> Unit
                }
            }
        }
    }
    composable(Routes.PW_LIST) {
        PWListScreen(pwListVM.pwState.collectAsState().value, pwListVM::onEvent, controller::navigateUp) {
            if(it != null) {
                controller.navigate(Routes.UPSERT_PW + "?id=${it}")
            } else controller.navigate(Routes.UPSERT_PW)
        }
    }
}