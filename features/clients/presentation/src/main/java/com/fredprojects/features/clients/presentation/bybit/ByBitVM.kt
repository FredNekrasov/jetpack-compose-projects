package com.fredprojects.features.clients.presentation.bybit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.features.clients.domain.bybit.models.BBInfo
import com.fredprojects.features.clients.domain.bybit.repository.IBBRepository
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ByBitVM(
    private val repository: IBBRepository
) : ViewModel() {
    private val bbStateMSF = MutableStateFlow<ConnectionStatus<BBInfo>>(ConnectionStatus.Nothing())
    val bbState = bbStateMSF.asStateFlow()
    fun getData() {
        viewModelScope.launch {
            repository.getData().flowOn(Dispatchers.IO).collect {
                bbStateMSF.emit(it)
            }
        }
    }
    fun update(bbInfo: BBInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRecord(bbInfo)
        }
    }
}