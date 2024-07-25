package com.fredprojects.helloworld.presentation.features.pws.vm

import androidx.lifecycle.*
import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import com.fredprojects.helloworld.domain.features.pws.useCases.PWUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UpsertPWVM(
    private val useCases: PWUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val upsertPWStateMSF = MutableStateFlow(UpsertPWState())
    val upsertPWState = upsertPWStateMSF.asStateFlow()
    fun upsert(pw: PracticalWork) {
        viewModelScope.launch {
            upsertPWStateMSF.emit(upsertPWState.value.copy(status = useCases.upsert(pw)))
        }
    }
    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            if (id != -1) {
                viewModelScope.launch {
                    useCases.getPWs.getById(id)?.let {
                        upsertPWStateMSF.emit(upsertPWState.value.copy(pw = it))
                    }
                }
            }
        }
    }
}