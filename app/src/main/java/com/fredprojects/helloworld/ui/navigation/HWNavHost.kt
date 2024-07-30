package com.fredprojects.helloworld.ui.navigation

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.content.FileProvider
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.fredprojects.helloworld.FibSequenceService
import com.fredprojects.helloworld.presentation.core.Action
import com.fredprojects.helloworld.presentation.core.displayToast
import com.fredprojects.helloworld.presentation.features.fibonacci.AnswerFibScreen
import com.fredprojects.helloworld.presentation.features.pws.UpsertPWScreen
import com.fredprojects.helloworld.presentation.features.pws.vm.UpsertPWVM
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.util.UUID
import com.fredprojects.helloworld.presentation.R as PR

@Composable
fun HWNavHost(
    activityContext: ComponentActivity,
    jumpingRopeScreen: @Composable (Action) -> Unit
) {
    val controller = rememberNavController()
    val upsertPWVM: UpsertPWVM = koinViewModel()
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
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
                if(!it) activityContext.displayToast(PR.string.error)
            }
            UpsertPWScreen(upsertPWVM.upsertPWState.collectAsState().value, controller::navigateUp, upsertPWVM::upsert) {
                val file = File(activityContext.filesDir, "fred${UUID.randomUUID()}.jpg")
                val uri = FileProvider.getUriForFile(activityContext, "fredProjectsHW.provider", file)
                if(activityContext.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) launcher.launch(uri)
                uri
            }
        }
    }
}