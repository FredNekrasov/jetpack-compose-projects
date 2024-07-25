package com.fredprojects.helloworld.presentation.features.jumps.rope

import android.hardware.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.presentation.core.*
import com.fredprojects.helloworld.presentation.features.jumps.JDIconButton
import com.fredprojects.helloworld.presentation.features.jumps.rope.vm.JREvents
import com.fredprojects.helloworld.presentation.features.jumps.rope.vm.JRState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.sqrt

@Composable
fun JumpingRopeScreen(
    state: JRState,
    onEvent: (JREvents) -> Unit
) {
    val scope = rememberCoroutineScope()
    val xFlow = MutableStateFlow(0f)
    val yFlow = MutableStateFlow(0f)
    val zFlow = MutableStateFlow(0f)
    val tFlow = MutableStateFlow(0L)
    object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if(event == null) return
            scope.launch {
                xFlow.emit(event.values[0])
                yFlow.emit(event.values[1])
                zFlow.emit(event.values[2])
                tFlow.emit(event.timestamp)
            }
        }
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
    }
    val x by xFlow.collectAsState()
    val y by yFlow.collectAsState()
    val z by zFlow.collectAsState()
    val t by tFlow.collectAsState()
    var accelerationCurrent by rememberSaveable { mutableFloatStateOf(0f) }
    var accelerationLast by rememberSaveable { mutableFloatStateOf(0f) }
    var countOfJumps by rememberSaveable { mutableIntStateOf(0) }
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        val alpha by rememberSaveable { mutableFloatStateOf(0.8f) }
        val gravity = sqrt((alpha * x * x + alpha * y * y + alpha * z * z))
        accelerationLast = accelerationCurrent
        accelerationCurrent = z - gravity
        val diff = accelerationCurrent - accelerationLast
        if (diff > 6) countOfJumps++
        FredText("x: $x")
        FredText("y: $y")
        FredText("z: $z")
        FredText("timestamp: $t")
        FredText("${stringResource(R.string.count)}: $countOfJumps")
        FredButton({ onEvent(JREvents.InsertJD(countOfJumps)) }, stringResource(R.string.save))
        FredButton({ onEvent(JREvents.SwitchingDialog) }, stringResource(R.string.jump))
    }
    if (state.isShowDialog) JumpDialog({ onEvent(JREvents.SwitchingDialog) }, countOfJumps)
}
@Composable
private fun JumpDialog(closeDialog: Action, countOfJumps: Int) {
    Dialog(closeDialog) {
        Column(Modifier.wrapContentSize().background(MaterialTheme.colorScheme.background), Arrangement.Center, Alignment.CenterHorizontally) {
            StickMan(countOfJumps)
            JDIconButton(closeDialog, Icons.Default.ArrowBackIosNew)
        }
    }
}