package com.fredprojects.helloworld.presentation.features.pws.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.helloworld.domain.core.utils.SortType
import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import com.fredprojects.helloworld.domain.features.pws.useCases.PWUseCases
import com.fredprojects.helloworld.domain.features.pws.utils.SortingPW
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PWListVM @Inject constructor(
    private val useCases: PWUseCases
) : ViewModel()  {
    private val pwStateMSF = MutableStateFlow(PWState())
    val pwState = pwStateMSF.asStateFlow()
    private var recentlyDeletedPW: PracticalWork? = null
    private var getDataJob: Job? = null
    fun onEvent(event: PWEvents) {
        when(event) {
            is PWEvents.DeletePW -> deletePW(event.pw)
            PWEvents.RestorePW -> restorePW()
            is PWEvents.SearchPW -> findPWsByValue(event.value)
            is PWEvents.Sort -> getSortedPWs(event.sortingPW)
            PWEvents.ToggleSortSection -> pwStateMSF.value = pwState.value.copy(isSortingSectionVisible = !pwState.value.isSortingSectionVisible)
        }
    }
    private fun getSortedPWs(sortingPW: SortingPW) {
        if ((pwState.value.sortingPW::class == sortingPW::class) && (pwState.value.sortingPW.sortType == sortingPW.sortType)) return
        getDataJob?.cancel()
        getDataJob = useCases.getPWs(sortingPW).onEach {
            pwStateMSF.emit(pwState.value.copy(pws = it, sortingPW = sortingPW))
        }.launchIn(viewModelScope)
    }
    private fun findPWsByValue(value: String) {
        getDataJob?.cancel()
        getDataJob = useCases.getPWs.find(value, pwState.value.sortingPW).onEach {
            pwStateMSF.emit(pwState.value.copy(pws = it))
        }.launchIn(viewModelScope)
    }
    private fun deletePW(pw: PracticalWork) {
        viewModelScope.launch {
            useCases.delete(pw)
            recentlyDeletedPW = pw
        }
    }
    private fun restorePW() {
        viewModelScope.launch {
            recentlyDeletedPW?.let { pw ->
                useCases.upsert(pw)
                recentlyDeletedPW = null
            }
        }
    }
    init {
        getSortedPWs(SortingPW.Date(SortType.Descending))
    }
}