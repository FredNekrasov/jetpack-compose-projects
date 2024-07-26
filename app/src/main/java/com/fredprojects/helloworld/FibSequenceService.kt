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
import org.koin.android.ext.android.get
import org.koin.core.qualifier.named
import kotlin.random.Random

class FibSequenceService : Service() {
    private fun sendResult(result: String) {
        val innerIntent = Intent(this@FibSequenceService, MainActivity::class.java)
        innerIntent.action = MainActivity.DISPLAY_RESULT
        innerIntent.putExtra(RESULT, result)
        val pendingIntent = PendingIntent.getActivity(this@FibSequenceService, 0, innerIntent, PendingIntent.FLAG_MUTABLE)
        val notification = NotificationCompat.Builder(this@FibSequenceService, MainActivity.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(getString(PR.string.displayResult))
            .setContentText(result)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.ic_launcher_background, getString(PR.string.displayResult), pendingIntent)
            .build()
        if(ActivityCompat.checkSelfPermission(this@FibSequenceService, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) return
        NotificationManagerCompat.from(this@FibSequenceService).notify(Random(Int.MAX_VALUE).nextInt(), notification)
    }
    override fun onBind(intent: Intent): IBinder {
        val binder = FibonacciBinder(get(named(AppQualifiers.FIBONACCI_USE_CASE)))
        binder.calculate(intent.getIntExtra(NUMBER, 0))
        sendResult(binder.result.value.second.toString())
        return binder
    }
    companion object {
        const val NUMBER = "number"
        const val RESULT = "result"
    }
}