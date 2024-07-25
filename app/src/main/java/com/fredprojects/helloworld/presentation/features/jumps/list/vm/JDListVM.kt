package com.fredprojects.helloworld.presentation.features.jumps.list.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.helloworld.domain.core.utils.SortType
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import com.fredprojects.helloworld.domain.features.jumps.useCases.JDUseCases
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
    private var getDataJob: Job? = null
    /**
     * The onEvent is used to manage data or view events
     */
    fun onEvent(event: JDEvents) {
        when(event) {
            is JDEvents.DeleteJD -> deleteJD(event.jumpData)
            is JDEvents.GetJD -> getJDById(event.jumpDataId)
            is JDEvents.Sort -> getSortedJDs(event.sortType)
            JDEvents.SwitchingDialog -> jdStateMSF.value = jdState.value.copy(isShowDialog = !jdState.value.isShowDialog)
            JDEvents.ToggleSortSection -> jdStateMSF.value = jdState.value.copy(isSortingSectionVisible = !jdState.value.isSortingSectionVisible)
            is JDEvents.UpsertJD -> upsertJD(event.jumpData)
        }
    }
    /**
     * The deleteJD is used to delete a jump from the database
     * @param jumpData is the jump to be deleted
     */
    private fun deleteJD(jumpData: JumpData) {
        viewModelScope.launch {
            useCases.delete(jumpData)
        }
    }
    /**
     * The getJDById is used to get a jump data from the database
     * @param jumpDataId is the id of the jump data
     */
    private fun getJDById(jumpDataId: Int) {
        viewModelScope.launch {
            jdStateMSF.emit(jdState.value.copy(jd = useCases.getData.getById(jumpDataId)))
        }
    }
    /**
     * The getSortedJDs is used to get the sorted list of jumps
     * @param sortType is the type of sorting
     */
    private fun getSortedJDs(sortType: SortType) {
        if (jdState.value.sortType == sortType) return
        getDataJob?.cancel()
        getDataJob = useCases.getData(sortType).onEach {
            jdStateMSF.emit(jdState.value.copy(jds = it, sortType = sortType))
        }.launchIn(viewModelScope)
    }
    /**
     * The upsertJD is used to insert or update a jump data
     * @param jumpData is the jump data to be inserted or updated
     */
    private fun upsertJD(jumpData: JumpData) {
        viewModelScope.launch {
            jdStateMSF.emit(jdState.value.copy(jdStatus = useCases.upsert(jumpData)))
        }
    }
    /**
     * The init is used to get the sorted list of jumps on creation of the class
     */
    init { getSortedJDs(SortType.Descending) }
}