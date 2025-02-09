package com.fredprojects.features.jump.presentation.menu.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.features.jump.domain.models.JumpData
import com.fredprojects.features.jump.domain.usecases.JDUseCases
import com.fredprojects.features.jump.domain.utils.JumpStatus
import com.fredprojects.features.jump.domain.utils.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * JDListVM is used to manage the state of the list of jumps
 */
@HiltViewModel
class JDListVM @Inject constructor(
    private val useCases: JDUseCases
) : ViewModel() {
    /**
     * @see jdStateMSF is used to emit data to the state flow
     * @see jdState is used to display data in the view
     */
    private val jdStateMSF = MutableStateFlow(JDState())
    val jdState = jdStateMSF.asStateFlow()
    private val jdStatusMSF = MutableSharedFlow<JumpStatus>()
    val jdStatus = jdStatusMSF.asSharedFlow()
    private var getDataJob: Job? = null
    /** The onEvent is used to manage data or view events **/
    fun onEvent(event: JDEvents) {
        when(event) {
            is JDEvents.DeleteJD -> deleteJD(event.jumpData)
            is JDEvents.GetJD -> jdStateMSF.value = jdState.value.copy(jd = event.jumpData)
            is JDEvents.Sort -> {
                if (jdState.value.sortType == event.sortType) return
                getSortedJDs(event.sortType)
            }
            is JDEvents.ToggleSortSection -> jdStateMSF.value = jdState.value.copy(isSortingSectionVisible = !jdState.value.isSortingSectionVisible)
            is JDEvents.UpsertJD -> upsertJD(event.jumpData)
            is JDEvents.SwitchingDialog -> jdStateMSF.value = jdState.value.copy(isDialogVisible = !jdState.value.isDialogVisible)
        }
    }
    private fun deleteJD(jumpData: JumpData) {
        viewModelScope.launch {
            useCases.delete.invoke(jumpData)
        }
    }
    private fun getSortedJDs(sortType: SortType) {
        getDataJob?.cancel()
        getDataJob = useCases.getData.invoke(sortType).onEach {
            jdStateMSF.emit(jdState.value.copy(jds = it, sortType = sortType))
        }.launchIn(viewModelScope)
    }
    private fun upsertJD(jumpData: JumpData) {
        viewModelScope.launch {
            jdStatusMSF.emit(useCases.upsert.invoke(jumpData))
        }
    }
    /**
     * The init is used to get the sorted list of jumps on creation of the class
     */
    init { getSortedJDs(SortType.Descending) }
}