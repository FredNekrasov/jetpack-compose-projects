package com.fredprojects.helloworld

import android.Manifest
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import androidx.core.app.*
import com.fredprojects.helloworld.di.AppQualifiers
import com.fredprojects.helloworld.presentation.R as PR
import com.fredprojects.helloworld.presentation.features.fibonacci.FibonacciBinder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.android.ext.android.get
import org.koin.core.qualifier.named
import kotlin.random.Random

class FibSequenceService : Service() {
    override fun onBind(intent: Intent): IBinder {
        val fibonacciBinder = FibonacciBinder(get(named(AppQualifiers.FIBONACCI_USE_CASE)))
        fibonacciBinder.calculate(intent.getIntExtra(NUMBER, -1))
        sendResult(fibonacciBinder)
        return fibonacciBinder
    }
    private fun sendResult(fibonacciBinder: FibonacciBinder) {
        fibonacciBinder.result.onEach { result ->
            val intent = Intent(this, MainActivity::class.java)
            intent.action = MainActivity.DISPLAY_RESULT
            intent.putExtra(RESULT, result.second.toString())
            sendMessage(intent, result.second.toString())
        }.launchIn(CoroutineScope(Dispatchers.Default))
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