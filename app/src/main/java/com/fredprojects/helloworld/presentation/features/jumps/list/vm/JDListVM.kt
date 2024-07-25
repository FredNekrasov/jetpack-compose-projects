package com.fredprojects.helloworld.presentation.features.jumps.list.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.helloworld.domain.core.utils.SortType
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import com.fredprojects.helloworld.domain.features.jumps.useCases.JDUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class JDListVM(
    private val useCases: JDUseCases
) : ViewModel() {
    private val jdStateMSF = MutableStateFlow(JDState())
    val jdState = jdStateMSF.asStateFlow()
    private var getDataJob: Job? = null
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
    private fun deleteJD(jumpData: JumpData) {
        viewModelScope.launch {
            useCases.delete(jumpData)
        }
    }
    private fun getJDById(jumpDataId: Int) {
        viewModelScope.launch {
            jdStateMSF.emit(jdState.value.copy(jd = useCases.getData.getById(jumpDataId)))
        }
    }
    private fun getSortedJDs(sortType: SortType) {
        if (jdState.value.sortType == sortType) return
        getDataJob?.cancel()
        getDataJob = useCases.getData(sortType).onEach {
            jdStateMSF.emit(jdState.value.copy(jds = it, sortType = sortType))
        }.launchIn(viewModelScope)
    }
    private fun upsertJD(jumpData: JumpData) {
        viewModelScope.launch {
            jdStateMSF.emit(jdState.value.copy(jdStatus = useCases.upsert(jumpData)))
        }
    }
    init { getSortedJDs(SortType.Descending) }
}