package com.fredprojects.helloworld.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.*
import com.fredprojects.features.auth.presentation.vm.UserVM
import com.fredprojects.features.clients.presentation.astronomy.AstronomyInfoVM
import com.fredprojects.features.clients.presentation.bybit.ByBitVM
import com.fredprojects.features.clients.presentation.math.MathVM
import com.fredprojects.features.inequality.impl.InequalityVM
import com.fredprojects.features.jumps.presentation.vm.JDListVM
import com.fredprojects.features.jumps.presentation.vm.JumpingRopeVM
import com.fredprojects.features.pws.presentation.vm.PWListVM
import com.fredprojects.features.pws.presentation.vm.UpsertPWVM
import com.fredprojects.helloworld.ui.navigation.modules.*

@Composable
fun HWNavHost(
    activityContext: ComponentActivity,
    jumpingRopeVM: JumpingRopeVM
) {
    val controller = rememberNavController()
    val userVM: UserVM = hiltViewModel()
    val inequalityVM: InequalityVM = hiltViewModel()
    val mathVM: MathVM = hiltViewModel()
    val astronomyInfoVM: AstronomyInfoVM = hiltViewModel()
    val jdListVM: JDListVM = hiltViewModel()
    val pwListVM: PWListVM = hiltViewModel()
    val upsertPWVM: UpsertPWVM = hiltViewModel()
    val bbVM: ByBitVM = hiltViewModel()
    NavHost(navController = controller, startDestination = Routes.AUTH) {
        authModule(userVM, activityContext, controller)
        composable(Routes.MAIN_SCREEN) {
            MainScreen(inequalityVM, mathVM, astronomyInfoVM, controller::navigateUp)
        }
        pwsModule(pwListVM, upsertPWVM, activityContext, controller)
        jumpsModule(jdListVM, controller, jumpingRopeVM)
        fibModule(activityContext, controller::navigateUp)
        byBitScreens(bbVM, controller)
    }
}