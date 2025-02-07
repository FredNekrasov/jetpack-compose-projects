package com.fredprojects.features.jump.presentation.jump

import android.app.Activity.SENSOR_SERVICE
import android.app.Application
import android.hardware.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.features.jump.domain.models.JumpData
import com.fredprojects.features.jump.domain.usecases.CalculateJumpUseCase
import com.fredprojects.features.jump.domain.usecases.crud.UpsertJDUseCase
import com.fredprojects.features.jump.domain.utils.JumpStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JumpingRopeVM @Inject constructor(
    private val upsertJDUseCase: UpsertJDUseCase,
    private val calculateJumpUseCase: CalculateJumpUseCase,
    appContext: Application
) : ViewModel() {
    private val sensorManager by lazy { appContext.getSystemService(SENSOR_SERVICE) as SensorManager }
    private val accelerometer by lazy { sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) }
    private val accelerometerEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if(event == null) return
            viewModelScope.launch {
                val result = calculateJumpUseCase.invoke(event.values[0], event.values[1], event.values[2])
                resultMSF.emit(result)
            }
        }
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit
    }
    /** This method is used to register the accelerometer sensor and start listening for changes **/
    fun registerAccelerometer() = sensorManager.registerListener(accelerometerEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    /** This method is used to unregister the accelerometer sensor and stop listening for changes **/
    fun unregisterAccelerometer() = sensorManager.unregisterListener(accelerometerEventListener)

    private val jrStateMSF = MutableSharedFlow<JumpStatus>()
    val jrState = jrStateMSF.asSharedFlow()
    private val resultMSF = MutableStateFlow(0)
    val resultSF = resultMSF.asStateFlow()

    fun insertJD() {
        viewModelScope.launch {
            jrStateMSF.emit(upsertJDUseCase.invoke(JumpData(resultSF.value)))
        }
    }
}