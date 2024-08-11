package com.fredprojects.helloworld.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.fredprojects.core.ui.Action
import com.fredprojects.features.auth.presentation.vm.UserVM
import com.fredprojects.features.clients.presentation.bybit.ByBitVM
import com.fredprojects.helloworld.ui.navigation.modules.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun HWNavHost(
    activityContext: ComponentActivity,
    jumpingRopeScreen: @Composable (Action) -> Unit
) {
    val controller = rememberNavController()
    val userVM: UserVM = koinViewModel()
    val bbVM: ByBitVM = koinViewModel()
    NavHost(navController = controller, startDestination = Routes.AUTH) {
        authModule(userVM, activityContext, controller)
        composable(Routes.MAIN_SCREEN) { MainScreen() }
        jumpsModule(controller, jumpingRopeScreen)
        byBitScreens(bbVM, controller)
        pwsModule(activityContext, controller)
        fibModule(activityContext)
    }
}