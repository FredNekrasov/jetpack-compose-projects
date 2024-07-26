package com.fredprojects.helloworld

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.*
import android.content.pm.PackageManager
import android.os.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.core.content.FileProvider
import com.fredprojects.helloworld.presentation.R as PR
import com.fredprojects.helloworld.presentation.core.displayToast
import com.fredprojects.helloworld.presentation.features.fibonacci.*
import com.fredprojects.helloworld.ui.navigation.MainEntryPoint
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme
import java.io.File
import java.util.UUID

class MainActivity : ComponentActivity() {
    private var fibSequences = listOf<FibonacciBinder>()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        val launcher = registerForActivityResult(ActivityResultContracts.TakePicture()) { if(!it) displayToast(getString(PR.string.error)) }
        val takePicture = {
            val file = File(filesDir, "fred${UUID.randomUUID()}.jpg")
            val uri = FileProvider.getUriForFile(this, "fred.provider", file)
            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) launcher.launch(uri)
            uri
        }
        val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            takePicture()
            sendResult("4")
        }
        permissionLauncher.launch(arrayOf(Manifest.permission.CAMERA, Manifest.permission.POST_NOTIFICATIONS))
        enableEdgeToEdge()
        setContent {
            HelloWorldTheme {
                var fibonacciSequences by rememberSaveable { mutableStateOf(fibSequences) }
                MainEntryPoint(takePicture) {
                    if(intent.action == DISPLAY_RESULT) {
                        AnswerFibScreen(intent.getStringExtra(FibSequenceService.RESULT).toString())
                    } else FibScreen(fibonacciSequences) { fibonacciSequences = sendResult(it) }
                }
            }
        }
    }
    private fun createNotificationChannel() {
        val channel = NotificationChannel(CHANNEL_ID, getString(R.string.fibMessageHeader), NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = getString(R.string.fibMessageDescription)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
    private fun sendResult(number: String): List<FibonacciBinder> {
        val intent = Intent(this, FibSequenceService::class.java)
        intent.action = UUID.randomUUID().toString()
        intent.putExtra(FibSequenceService.NUMBER, number.toIntOrNull() ?: 4)
        val serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(p0: ComponentName?, bind: IBinder?) {
                val localBinder = bind as? FibonacciBinder
                if (localBinder != null) fibSequences += listOf(localBinder)
            }
            override fun onServiceDisconnected(p0: ComponentName?) = displayToast(getString(R.string.fibServiceError))
        }
        bindService(intent, serviceConnection, BIND_AUTO_CREATE)
        return fibSequences
    }
    companion object {
        const val DISPLAY_RESULT = "display_result"
        const val CHANNEL_ID = "fred49"
    }
}