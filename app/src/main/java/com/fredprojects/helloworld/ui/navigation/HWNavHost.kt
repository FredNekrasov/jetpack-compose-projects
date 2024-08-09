package com.fredprojects.helloworld.ui.navigation

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.core.content.FileProvider
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.fibonacci.impl.AnswerFibScreen
import com.fredprojects.features.pws.domain.utils.PWStatus
import com.fredprojects.features.pws.presentation.UpsertPWScreen
import com.fredprojects.features.pws.presentation.vm.UpsertPWVM
import com.fredprojects.helloworld.FibSequenceService
import com.fredprojects.helloworld.ui.displayToast
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.util.UUID

@Composable
fun HWNavHost(
    activityContext: ComponentActivity,
    jumpingRopeScreen: @Composable (Action) -> Unit
) {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = Routes.MAIN_SCREEN) {
        composable(Routes.MAIN_SCREEN) {
            MainScreen(activityContext, controller::navigate)
        }
        composable(Routes.JUMPING_ROPE) {
            jumpingRopeScreen(controller::navigateUp)
        }
        composable(Routes.FIBONACCI) {
            AnswerFibScreen(
                activityContext.intent.getStringExtra(FibSequenceService.RESULT).toString(),
                activityContext::finish
            )
        }
        composable(Routes.UPSERT_PW + "?id={id}", listOf(navArgument("id") { type = NavType.IntType; defaultValue = -1 })) {
            val upsertPWVM: UpsertPWVM = koinViewModel()
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
                if(!it) activityContext.displayToast(R.string.error)
            }
            UpsertPWScreen(upsertPWVM.upsertPWState.collectAsState().value, controller::navigateUp, upsertPWVM::upsert) {
                val file = File(activityContext.filesDir, "fred${UUID.randomUUID()}.jpg")
                val uri = FileProvider.getUriForFile(activityContext, "fredProjectsHW.provider", file)
                if(activityContext.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) launcher.launch(uri)
                uri
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
    }
}