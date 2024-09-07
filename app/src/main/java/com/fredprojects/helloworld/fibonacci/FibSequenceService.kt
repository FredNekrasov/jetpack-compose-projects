package com.fredprojects.helloworld.fibonacci

import android.Manifest
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import androidx.core.app.*
import com.fredprojects.features.fibonacci.api.useCases.FibonacciUseCase
import com.fredprojects.core.ui.R as PR
import com.fredprojects.features.fibonacci.impl.FibonacciBinder
import com.fredprojects.helloworld.MainActivity
import com.fredprojects.helloworld.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class FibSequenceService : Service() {
    @Inject lateinit var fubUseCase: FibonacciUseCase
    override fun onBind(intent: Intent): IBinder {
        val fibonacciBinder = FibonacciBinder(fubUseCase)
        fibonacciBinder.calculate(intent.getIntExtra(NUMBER, -1))
        sendResult(fibonacciBinder)
        return fibonacciBinder
    }
    private fun sendResult(fibonacciBinder: FibonacciBinder) {
        val intent = Intent(this, MainActivity::class.java)
        intent.action = MainActivity.DISPLAY_RESULT
        CoroutineScope(Dispatchers.Default).launch {
            fibonacciBinder.result.collectLatest {
                intent.putExtra(RESULT, it.second.toString())
                sendMessage(intent, it.second.toString())
            }
        }
    }
    private fun sendMessage(intent: Intent, result: String) {
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        val notification = NotificationCompat.Builder(this, MainActivity.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(getString(PR.string.displayResult))
            .setContentText(result)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(R.drawable.ic_launcher_background, getString(PR.string.displayResult), pendingIntent)
            .build()
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) return
        NotificationManagerCompat.from(this).notify(Random(Int.MAX_VALUE).nextInt(), notification)
    }
    companion object {
        const val NUMBER = "number"
        const val RESULT = "result"
    }
}