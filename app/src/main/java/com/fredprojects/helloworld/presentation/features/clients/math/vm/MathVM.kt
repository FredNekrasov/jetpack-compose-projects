package com.fredprojects.helloworld.presentation.features.clients.math.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.helloworld.domain.core.repositories.IClientRepository
import com.fredprojects.helloworld.domain.core.utils.ConnectionStatus
import com.fredprojects.helloworld.domain.features.clients.common.MathModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MathVM(
    private val repository: IClientRepository<MathModel>
) : ViewModel() {
    private val mathInfoMSF = MutableStateFlow<ConnectionStatus<MathModel>>(ConnectionStatus.Loading(emptyList()))
    val mathInfo = mathInfoMSF.asStateFlow()
    fun getMathInfo(data: String) {
        viewModelScope.launch {
            repository.getData(data).flowOn(Dispatchers.IO).collectLatest {
                mathInfoMSF.emit(it)
            }
        }
    }
}