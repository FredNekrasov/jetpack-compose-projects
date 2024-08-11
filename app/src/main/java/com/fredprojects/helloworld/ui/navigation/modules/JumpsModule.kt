package com.fredprojects.helloworld.ui.navigation.modules

import androidx.compose.runtime.*
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fredprojects.core.ui.Action
import com.fredprojects.features.jumps.domain.utils.JumpStatus
import com.fredprojects.features.jumps.presentation.JDDialog
import com.fredprojects.features.jumps.presentation.JDListScreen
import com.fredprojects.features.jumps.presentation.vm.JDEvents
import com.fredprojects.features.jumps.presentation.vm.JDListVM
import com.fredprojects.helloworld.ui.navigation.Routes
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.jumpsModule(
    controller: NavHostController,
    jumpingRopeScreen: @Composable (Action) -> Unit
) {
    composable(Routes.JD_LIST) {
        val jdListVM: JDListVM = koinViewModel()
        val jdState = jdListVM.jdState.collectAsState().value
        var isValuesCorrect by remember { mutableStateOf(true) }
        JDListScreen(jdState, jdListVM::onEvent) {
            controller.navigate(Routes.JUMPING_ROPE)
        }
        if(jdState.isDialogVisible) JDDialog(jdState, jdListVM::onEvent, isValuesCorrect)
        LaunchedEffect(key1 = true) {
            jdListVM.jdStatus.collectLatest {
                when(it) {
                    JumpStatus.SUCCESS -> {
                        isValuesCorrect = true
                        jdListVM.onEvent(JDEvents.SwitchingDialog)
                    }
                    JumpStatus.NOTHING -> isValuesCorrect = true
                    else -> isValuesCorrect = false
                }
            }
        }
    }
    composable(Routes.JUMPING_ROPE) {
        jumpingRopeScreen(controller::navigateUp)
    }
}