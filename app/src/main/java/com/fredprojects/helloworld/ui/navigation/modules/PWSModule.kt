package com.fredprojects.helloworld.ui.navigation.modules

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.fredprojects.core.ui.R
import com.fredprojects.features.pws.domain.utils.PWStatus
import com.fredprojects.features.pws.presentation.*
import com.fredprojects.features.pws.presentation.vm.PWListVM
import com.fredprojects.features.pws.presentation.vm.UpsertPWVM
import com.fredprojects.helloworld.ui.displayToast
import com.fredprojects.helloworld.ui.navigation.Routes
import kotlinx.coroutines.flow.collectLatest
import java.io.File
import java.util.UUID

fun NavGraphBuilder.pwsModule(
    pwListVM: PWListVM,
    activityContext: ComponentActivity,
    controller: NavHostController
) {
    composable(
        Routes.UPSERT_PW + "?id={id}",
        listOf(
            navArgument("id") {
                type = NavType.IntType
                defaultValue = -1
            }
        )
    ) {
        LocalUpsertPWScreen(activityContext, controller::navigateUp)
    }
    composable(Routes.PW_LIST) {
        PWListScreen(pwListVM.pwState.collectAsState().value, pwListVM::onEvent, controller::navigateUp) { id ->
            id?.let { controller.navigate(Routes.UPSERT_PW + "?id=$it") } ?: controller.navigate(Routes.UPSERT_PW)
        }
    }
}
@Composable
private fun LocalUpsertPWScreen(
    activityContext: ComponentActivity,
    goBack: () -> Unit,
    upsertPWVM: UpsertPWVM = hiltViewModel()
) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        if(!it) activityContext.displayToast(R.string.error)
    }
    UpsertPWScreen(upsertPWVM.upsertPWState.collectAsState().value, goBack, upsertPWVM::upsert) {
        val file = File(activityContext.filesDir, "fred${UUID.randomUUID()}.jpg")
        val uri = FileProvider.getUriForFile(activityContext, "fredProjectsHW.provider", file)
        if(activityContext.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) launcher.launch(uri)
        uri
    }
    LaunchedEffect(key1 = true) {
        upsertPWVM.upsertPWState.collectLatest {
            when(it.status) {
                PWStatus.SUCCESS -> goBack()
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