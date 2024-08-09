package com.fredprojects.helloworld

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.hardware.*
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import com.fredprojects.core.ui.R as PR
import com.fredprojects.core.ui.*
import com.fredprojects.features.jumps.domain.utils.JumpStatus
import com.fredprojects.features.jumps.presentation.JumpingRopeDialog
import com.fredprojects.features.jumps.presentation.vm.JumpingRopeVM
import com.fredprojects.helloworld.ui.navigation.HWNavHost
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    private val sensorManager by lazy { getSystemService(SENSOR_SERVICE) as SensorManager }
    private val accelerometer by lazy { sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) }
    private val xMSF = MutableStateFlow(0f)
    private val yMSF = MutableStateFlow(0f)
    private val zMSF = MutableStateFlow(0f)
    private var accelerationCurrent = 0f
    private var accelerationLast = 0f
    private val accelerometerEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if(event == null) return
            lifecycleScope.launch {
                xMSF.emit(event.values[0])
                yMSF.emit(event.values[1])
                zMSF.emit(event.values[2])
            }
        }
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val channel = NotificationChannel(CHANNEL_ID, getString(R.string.fibMessageHeader), NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = getString(R.string.fibMessageDescription)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            // todo auth
        }
        permissionLauncher.launch(arrayOf(Manifest.permission.CAMERA, Manifest.permission.POST_NOTIFICATIONS))
        sensorManager.registerListener(accelerometerEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        setContent {
            HelloWorldTheme {
                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val jumpingRopeVM: JumpingRopeVM = koinViewModel(named<JumpingRopeVM>())
                    HWNavHost(activityContext = this) { goBack ->
                        val x = xMSF.collectAsState().value
                        val y = yMSF.collectAsState().value
                        val z = zMSF.collectAsState().value
                        var isShowDialog by rememberSaveable { mutableStateOf(false) }
                        var numberOfJumps by rememberSaveable { mutableIntStateOf(0) }
                        Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                            val alpha = 0.8f
                            val gravity = sqrt((alpha * x * x + alpha * y * y + alpha * z * z))
                            accelerationLast = accelerationCurrent
                            accelerationCurrent = z - gravity
                            val diff = accelerationCurrent - accelerationLast
                            if (diff > 6) numberOfJumps++
                            FredText("x: $x")
                            FredText("y: $y")
                            FredText("z: $z")
                            FredText("${stringResource(PR.string.count)}: $numberOfJumps")
                            FredButton({ jumpingRopeVM.insertJD(numberOfJumps) }, stringResource(PR.string.save))
                            FredButton({ isShowDialog = !isShowDialog }, stringResource(PR.string.jump))
                            LaunchedEffect(key1 = true) {
                                jumpingRopeVM.jrState.collectLatest { if(it == JumpStatus.SUCCESS) goBack() }
                            }
                        }
                        if (isShowDialog) JumpingRopeDialog(numberOfJumps) { isShowDialog = !isShowDialog }
                    }
                }
            }
        }
    }
    override fun onStop() {
        super.onStop()
        sensorManager.unregisterListener(accelerometerEventListener)
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