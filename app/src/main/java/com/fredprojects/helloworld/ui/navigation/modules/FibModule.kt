package com.fredprojects.helloworld.ui.navigation.modules

import android.app.Activity.BIND_AUTO_CREATE
import androidx.activity.ComponentActivity
import android.content.*
import android.os.IBinder
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fredprojects.features.fibonacci.impl.*
import com.fredprojects.helloworld.FibSequenceService
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.ui.displayToast
import com.fredprojects.helloworld.ui.navigation.Routes
import java.util.UUID

fun NavGraphBuilder.fibModule(
    activityContext: ComponentActivity
) {
    composable(Routes.FIBONACCI) {
        var fibSequences by rememberSaveable { mutableStateOf(emptyList<FibonacciBinder>()) }
        FibScreen(fibSequences) {
            activityContext.apply {
                val intent = Intent(this, FibSequenceService::class.java)
                intent.action = UUID.randomUUID().toString()
                intent.putExtra(FibSequenceService.NUMBER, it)
                val serviceConnection = object : ServiceConnection {
                    override fun onServiceConnected(p0: ComponentName?, bind: IBinder?) {
                        val localBinder = bind as? FibonacciBinder
                        if (localBinder != null) fibSequences += listOf(localBinder)
                    }
                    override fun onServiceDisconnected(p0: ComponentName?) = displayToast(R.string.fibServiceError)
                }
                bindService(intent, serviceConnection, BIND_AUTO_CREATE)
            }
        }
    }
    composable(Routes.RESULT_FIB) {
        AnswerFibScreen(
            activityContext.intent.getStringExtra(FibSequenceService.RESULT).toString(),
            activityContext::finish
        )
    }
}