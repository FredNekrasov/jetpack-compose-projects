package com.fredprojects.features.jumps.presentation.vm

import android.app.Application
import android.app.Activity.SENSOR_SERVICE
import android.hardware.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.features.jumps.domain.models.JumpData
import com.fredprojects.features.jumps.domain.utils.JumpStatus
import com.fredprojects.features.jumps.domain.useCases.JDUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.math.sqrt
import javax.inject.Inject

/**
 * JumpingRopeVM is used to manage the state of the jumping rope
 */
@HiltViewModel
class JumpingRopeVM @Inject constructor(
    private val useCases: JDUseCases,
    appContext: Application
) : ViewModel() {
    /**
     * @see jrStateMSF is used to emit data to the state flow
     * @see jrState is used to display data in the view
     */
    private val jrStateMSF = MutableSharedFlow<JumpStatus>()
    val jrState = jrStateMSF.asSharedFlow()
    private val resultMSF = MutableStateFlow(0)
    val resultSF = resultMSF.asStateFlow()
    /**
     * The insertJD is used to insert a jump data into the database
     */
    fun insertJD() {
        viewModelScope.launch {
            jrStateMSF.emit(useCases.upsert(JumpData(resultSF.value)))
        }
    }
    private val sensorManager by lazy { appContext.getSystemService(SENSOR_SERVICE) as SensorManager }
    private val accelerometer by lazy { sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) }
    private val xMSF = MutableStateFlow(0f)
    private val yMSF = MutableStateFlow(0f)
    private val zMSF = MutableStateFlow(0f)
    private var accelerationCurrent = 0f
    private var accelerationLast = 0f
    private var gravity = 0f
    private var diff = 0f
    val xSF = xMSF.asStateFlow()
    val ySF = yMSF.asStateFlow()
    val zSF = zMSF.asStateFlow()
    private val accelerometerEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if(event == null) return
            viewModelScope.launch {
                xMSF.emit(event.values[0])
                yMSF.emit(event.values[1])
                zMSF.emit(event.values[2])
                gravity = sqrt(ALPHA * xSF.value * xSF.value + ALPHA * ySF.value * ySF.value + ALPHA * zSF.value * zSF.value)
                accelerationLast = accelerationCurrent
                accelerationCurrent = zSF.value - gravity
                diff = accelerationCurrent - accelerationLast
                if (diff > 6) resultMSF.value++
            }
        }
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit
    }
    /**
     * The registerAccelerometer is used to register the accelerometer sensor and start listening for changes
     */
    fun registerAccelerometer() = sensorManager.registerListener(accelerometerEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    /**
     * The unregisterAccelerometer is used to unregister the accelerometer sensor and stop listening for changes
     */
    fun unregisterAccelerometer() = sensorManager.unregisterListener(accelerometerEventListener)
    companion object {
        private const val ALPHA = 0.8f
    }
}