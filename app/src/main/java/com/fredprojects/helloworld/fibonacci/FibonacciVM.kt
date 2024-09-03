package com.fredprojects.helloworld.fibonacci

import android.app.Activity.BIND_AUTO_CREATE
import android.app.Application
import android.content.*
import android.os.IBinder
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.fredprojects.features.fibonacci.impl.FibonacciBinder
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.ui.displayToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class FibonacciVM @Inject constructor(
    private val appContext: Application
) : ViewModel() {
    private val fibSequencesMSF = MutableStateFlow(emptyList<FibonacciBinder>())
    val fibSequencesSF = fibSequencesMSF.asStateFlow()
    fun sendNotification(number: Int) {
        appContext.apply {
            val intent = Intent(this, FibSequenceService::class.java)
            intent.action = UUID.randomUUID().toString()
            intent.putExtra(FibSequenceService.NUMBER, number)
            val serviceConnection = object : ServiceConnection {
                override fun onServiceConnected(p0: ComponentName?, bind: IBinder?) {
                    val localBinder = bind as? FibonacciBinder
                    if (localBinder != null) fibSequencesMSF.value += listOf(localBinder)
                }
                override fun onServiceDisconnected(p0: ComponentName?) = displayToast(R.string.fibServiceError)
            }
            bindService(intent, serviceConnection, BIND_AUTO_CREATE)
        }
    }
}