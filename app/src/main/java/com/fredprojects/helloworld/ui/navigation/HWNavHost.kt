package com.fredprojects.helloworld.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.*
import com.fredprojects.features.auth.presentation.vm.UserVM
import com.fredprojects.features.clients.presentation.astronomy.AstronomyInfoVM
import com.fredprojects.features.clients.presentation.bybit.ByBitVM
import com.fredprojects.features.clients.presentation.math.MathVM
import com.fredprojects.features.math.presentation.inequality.InequalityVM
import com.fredprojects.features.jump.presentation.menu.vm.JDListVM
import com.fredprojects.features.jump.presentation.jump.JumpingRopeVM
import com.fredprojects.features.pws.presentation.vm.PWListVM
import com.fredprojects.helloworld.fibonacci.FibonacciVM
import com.fredprojects.helloworld.ui.navigation.modules.*

@Composable
fun HWNavHost(
    activityContext: ComponentActivity,
    jumpingRopeVM: JumpingRopeVM,
    modifier: Modifier
) {
    val controller = rememberNavController()
    val userVM: UserVM = hiltViewModel()
    val inequalityVM: InequalityVM = hiltViewModel()
    val mathVM: MathVM = hiltViewModel()
    val astronomyInfoVM: AstronomyInfoVM = hiltViewModel()
    val jdListVM: JDListVM = hiltViewModel()
    val pwListVM: PWListVM = hiltViewModel()
    val fibVM: FibonacciVM = hiltViewModel()
    val bbVM: ByBitVM = hiltViewModel()
    NavHost(navController = controller, startDestination = Routes.AUTH, modifier = modifier) {
        authModule(userVM, activityContext, controller)
        composable(Routes.MAIN_SCREEN) {
            MainScreen(inequalityVM, mathVM, astronomyInfoVM, controller::navigateUp)
        }
        pwsModule(pwListVM, activityContext, controller)
        jumpsModule(jdListVM, controller, jumpingRopeVM)
        fibModule(fibVM, activityContext, controller::navigateUp)
        byBitScreens(bbVM, controller)
    }
}