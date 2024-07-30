package com.fredprojects.helloworld.presentation.features.jumps.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import com.fredprojects.helloworld.domain.features.jumps.useCases.JDUseCases
import com.fredprojects.helloworld.domain.features.jumps.utils.JumpStatus
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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
    private val jrStateMSF = MutableSharedFlow<JumpStatus>()
    val jrState = jrStateMSF.asSharedFlow()
    /**
     * The insertJD is used to insert a jump data into the database
     * @param countOfJumps is the number of jumps
     */
    fun insertJD(countOfJumps: Int) {
        viewModelScope.launch {
            jrStateMSF.emit(useCases.upsert(JumpData(countOfJumps)))
        }
    }
}