package com.fredprojects.features.jumps.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.features.jumps.domain.utils.*
import com.fredprojects.features.jumps.domain.useCases.JDUseCases
import com.fredprojects.features.jumps.presentation.mappers.*
import com.fredprojects.features.jumps.presentation.models.JDPModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * JDListVM is used to manage the state of the list of jumps
 */
class JDListVM(
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
    /**
     * The onEvent is used to manage data or view events
     */
    fun onEvent(event: JDEvents) {
        when(event) {
            is JDEvents.DeleteJD -> deleteJD(event.jumpData)
            is JDEvents.GetJD -> jdStateMSF.value = jdState.value.copy(jd = event.jumpData)
            is JDEvents.Sort -> {
                if (jdState.value.sortType == event.sortType) return
                getSortedJDs(event.sortType)
            }
            JDEvents.ToggleSortSection -> jdStateMSF.value = jdState.value.copy(isSortingSectionVisible = !jdState.value.isSortingSectionVisible)
            is JDEvents.UpsertJD -> upsertJD(event.jumpData)
            JDEvents.SwitchingDialog -> jdStateMSF.value = jdState.value.copy(isDialogVisible = !jdState.value.isDialogVisible)
        }
    }
    /**
     * The deleteJD is used to delete a jump from the database
     * @param jumpData is the jump to be deleted
     */
    private fun deleteJD(jumpData: JDPModel) {
        viewModelScope.launch {
            useCases.delete(jumpData.toDomain())
        }
    }
    /**
     * The getSortedJDs is used to get the sorted list of jumps
     * @param sortType is the type of sorting
     */
    private fun getSortedJDs(sortType: SortType) {
        getDataJob?.cancel()
        getDataJob = useCases.getData(sortType).onEach {
            jdStateMSF.emit(jdState.value.copy(jds = it.map { jd -> jd.toPresentation() }, sortType = sortType))
        }.launchIn(viewModelScope)
    }
    /**
     * The upsertJD is used to insert or update a jump data
     * @param jumpData is the jump data to be inserted or updated
     */
    private fun upsertJD(jumpData: JDPModel) {
        viewModelScope.launch {
            jdStatusMSF.emit(useCases.upsert(jumpData.toDomain()))
        }
    }
    /**
     * The init is used to get the sorted list of jumps on creation of the class
     */
    init { getSortedJDs(SortType.Descending) }
}