package com.fredprojects.helloworld.ui.navigation.modules

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.jump.domain.utils.JumpStatus
import com.fredprojects.features.jump.presentation.jump.*
import com.fredprojects.features.jump.presentation.menu.*
import com.fredprojects.features.jump.presentation.menu.vm.*
import com.fredprojects.helloworld.ui.navigation.Routes
import kotlinx.coroutines.flow.collectLatest

fun NavGraphBuilder.jumpsModule(
    jdListVM: JDListVM,
    controller: NavHostController,
    jumpingRopeVM: JumpingRopeVM
) {
    composable(Routes.JD_LIST) {
        val jdState = jdListVM.jdState.collectAsState().value
        var isValuesCorrect by remember { mutableStateOf(true) }
        JDListScreen(jdState, jdListVM::onEvent, controller::navigateUp) {
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
        var isShowDialog by rememberSaveable { mutableStateOf(false) }
        val numberOfJumps by jumpingRopeVM.resultSF.collectAsState()
        Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
            FredText("${stringResource(R.string.count)}: $numberOfJumps")
            FredButton(jumpingRopeVM::insertJD, stringResource(R.string.save))
            FredButton({ isShowDialog = !isShowDialog }, stringResource(R.string.jump))
            LaunchedEffect(key1 = true) {
                jumpingRopeVM.jrState.collectLatest { if(it == JumpStatus.SUCCESS) controller.navigateUp() }
            }
        }
        if (isShowDialog) JumpingRopeDialog(numberOfJumps) { isShowDialog = !isShowDialog }
    }
}