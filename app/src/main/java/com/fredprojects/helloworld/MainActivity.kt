package com.fredprojects.helloworld

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.fredprojects.features.jumps.presentation.vm.JumpingRopeVM
import com.fredprojects.helloworld.ui.displayToast
import com.fredprojects.helloworld.ui.navigation.HWNavHost
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val jumpingRopeVM: JumpingRopeVM by viewModels()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        jumpingRopeVM.registerAccelerometer()
        val channel = NotificationChannel(CHANNEL_ID, getString(R.string.fibMessageHeader), NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = getString(R.string.fibMessageDescription)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if(it.values.all { isGranted -> isGranted }) displayToast(R.string.permissionGranted)
        }.launch(arrayOf(Manifest.permission.CAMERA, Manifest.permission.POST_NOTIFICATIONS))
        setContent {
            HelloWorldTheme {
                Surface(Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues()), color = MaterialTheme.colors.background) {
                    HWNavHost(activityContext = this, jumpingRopeVM)
                }
            }
        }
    }
    override fun onStop() {
        super.onStop()
        jumpingRopeVM.unregisterAccelerometer()
    }
    companion object {
        const val DISPLAY_RESULT = "display_result"
        const val CHANNEL_ID = "fred49"
    }
}
/* getting information about sensors in your mobile device
    sensorManager.getSensorList(Sensor.TYPE_ALL).forEach {
        Log.d("fred", "name: ${it.name}, type: ${it.stringType}, vendor: ${it.vendor}, version: ${it.version}")
    }
*/