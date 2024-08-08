package com.fredprojects.features.clients.presentation.astronomy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.features.clients.domain.astronomy.models.AstronomyInfo
import com.fredprojects.features.clients.domain.astronomy.repository.IAstronomyRepository
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * AstronomyInfoVM is used to get data from the server
 * @param repository the repository used to get data from the server
 */
class AstronomyInfoVM(
    private val repository: IAstronomyRepository
) : ViewModel() {
    /**
     * @see astronomyInfoMSF is used to emit data to the state flow
     * @see astronomyInfo is used to display data in the view
     */
    private val astronomyInfoMSF = MutableStateFlow<ConnectionStatus<AstronomyInfo>>(ConnectionStatus.Nothing())
    val astronomyInfo = astronomyInfoMSF.asStateFlow()
    /**
     * getAstronomyInfo is used to get data from the server and emit it to the state flow
     * @param ra the right ascension to be sent to the server
     * @param dec the declination to be sent to the server
     * @param radius the radius to be sent to the server
     */
    fun getAstronomyInfo(ra: String, dec: String, radius: Float) {
        viewModelScope.launch {
            repository.getData(ra, dec, radius).flowOn(Dispatchers.IO).collectLatest {
                astronomyInfoMSF.emit(it)
            }
        }
    }
}