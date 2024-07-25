package com.fredprojects.helloworld.presentation.features.jumps.rope.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import com.fredprojects.helloworld.domain.features.jumps.useCases.JDUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * JumpingRopeVM is used to manage the state of the jumping rope
 */
class JumpingRopeVM(
    private val useCases: JDUseCases
) : ViewModel() {
    /**
     * @see jrStateMSF is used to emit data to the state flow
     * @see jrState is used to display data in the view
     */
    private val jrStateMSF = MutableStateFlow(JRState())
    val jrState = jrStateMSF.asStateFlow()
    /**
     * The onEvent is used to manage data or view events
     */
    fun onEvent(event: JREvents) {
        when(event) {
            is JREvents.InsertJD -> insertJD(event.countOfJumps)
            is JREvents.SwitchingDialog -> jrStateMSF.value = jrStateMSF.value.copy(isShowDialog = !jrStateMSF.value.isShowDialog)
        }
    }
    /**
     * The insertJD is used to insert a jump data into the database
     * @param countOfJumps is the number of jumps
     */
    private fun insertJD(countOfJumps: Int) {
        viewModelScope.launch {
            jrStateMSF.emit(jrStateMSF.value.copy(status = useCases.upsert(JumpData(countOfJumps))))
        }
    }
}