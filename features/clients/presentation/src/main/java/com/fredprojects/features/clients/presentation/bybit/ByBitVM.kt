package com.fredprojects.features.clients.presentation.bybit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.features.clients.domain.bybit.models.BBInfo
import com.fredprojects.features.clients.domain.bybit.repository.IBBRepository
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ByBitVM @Inject constructor(
    private val repository: IBBRepository
) : ViewModel() {
    private val bbStateMSF = MutableStateFlow<ConnectionStatus<BBInfo>>(ConnectionStatus.Nothing())
    val bbState = bbStateMSF.asStateFlow()
    init { getData() }
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