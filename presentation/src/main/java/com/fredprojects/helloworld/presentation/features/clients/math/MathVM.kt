package com.fredprojects.helloworld.presentation.features.clients.math

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.helloworld.domain.core.repositories.IClientRepository
import com.fredprojects.helloworld.domain.core.utils.ConnectionStatus
import com.fredprojects.helloworld.domain.features.clients.common.MathModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * MathVM is used to get data from the server
 * @param repository the repository used to get data from the server
 */
class MathVM(
    private val repository: IClientRepository<MathModel>
) : ViewModel() {
    /**
     * @see mathInfoMSF is used to emit data to the state flow
     * @see mathInfo is used to display data in the view
     */
    private val mathInfoMSF = MutableStateFlow<ConnectionStatus<MathModel>>(ConnectionStatus.Nothing())
    val mathInfo = mathInfoMSF.asStateFlow()
    /**
     * getMathInfo is used to get data from the server and emit it to the state flow
     * @param data the math expression to be sent to the server
     */
    fun getMathInfo(data: String) {
        viewModelScope.launch {
            repository.getData(data).flowOn(Dispatchers.IO).collectLatest {
                mathInfoMSF.emit(it)
            }
        }
    }
}