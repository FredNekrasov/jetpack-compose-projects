package com.fredprojects.helloworld.ui.navigation.modules

import androidx.activity.ComponentActivity
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fredprojects.features.fibonacci.impl.AnswerFibScreen
import com.fredprojects.features.fibonacci.impl.FibScreen
import com.fredprojects.helloworld.fibonacci.FibSequenceService
import com.fredprojects.helloworld.fibonacci.FibonacciVM
import com.fredprojects.helloworld.ui.navigation.Routes

fun NavGraphBuilder.fibModule(
    fibVM: FibonacciVM,
    activityContext: ComponentActivity,
    goBack: () -> Unit
) {
    composable(Routes.FIBONACCI) {
        FibScreen(fibVM.fibSequencesSF.collectAsState().value, goBack, fibVM::sendNotification)
    }
    composable(Routes.RESULT_FIB) {
        AnswerFibScreen(
            activityContext.intent.getStringExtra(FibSequenceService.RESULT).toString(),
            activityContext::finish
        )
    }
}