package com.fredprojects.features.jumps.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.features.jumps.domain.models.JumpData
import com.fredprojects.features.jumps.domain.utils.JumpStatus
import com.fredprojects.features.jumps.domain.useCases.JDUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * JumpingRopeVM is used to manage the state of the jumping rope
 */
@HiltViewModel
class JumpingRopeVM @Inject constructor(
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