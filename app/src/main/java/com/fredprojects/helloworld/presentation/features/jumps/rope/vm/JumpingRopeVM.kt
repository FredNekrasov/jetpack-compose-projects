package com.fredprojects.helloworld.presentation.features.jumps.rope.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import com.fredprojects.helloworld.domain.features.jumps.useCases.JDUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JumpingRopeVM(
    private val useCases: JDUseCases
) : ViewModel() {
    private val jrStateMSF = MutableStateFlow(JRState())
    val jrState = jrStateMSF.asStateFlow()
    fun onEvent(event: JREvents) {
        when(event) {
            is JREvents.InsertJD -> insertJD(event.countOfJumps)
            is JREvents.SwitchingDialog -> jrStateMSF.value = jrStateMSF.value.copy(isShowDialog = !jrStateMSF.value.isShowDialog)
        }
    }
    private fun insertJD(countOfJumps: Int) {
        viewModelScope.launch {
            jrStateMSF.emit(jrStateMSF.value.copy(status = useCases.upsert(JumpData(countOfJumps))))
        }
    }
}