package com.fredprojects.features.pws.presentation.vm

import androidx.lifecycle.*
import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.domain.useCases.PWUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpsertPWVM @Inject constructor(
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