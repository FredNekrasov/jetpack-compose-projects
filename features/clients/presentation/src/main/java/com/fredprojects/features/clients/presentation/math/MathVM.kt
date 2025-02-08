package com.fredprojects.features.clients.presentation.math

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.features.clients.domain.math.models.MathModel
import com.fredprojects.features.clients.domain.math.repository.IMathRepository
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MathVM @Inject constructor(
    private val repository: IMathRepository
) : ViewModel() {
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