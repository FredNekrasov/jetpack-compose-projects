package com.fredprojects.features.pws.presentation.vm

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.domain.useCases.PWUseCases
import com.fredprojects.features.pws.domain.utils.SortType
import com.fredprojects.features.pws.domain.utils.SortingPW
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
            PWEvents.ToggleSortSection -> pwStateMSF.value = pwState.value.copy(isSortingSectionVisible = !pwState.value.isSortingSectionVisible)
            is PWEvents.Sort -> {
                if ((pwState.value.sortingPW::class == event.sortingPW::class) && (pwState.value.sortingPW.sortType == event.sortingPW.sortType)) return
                getSortedPWs(event.sortingPW)
            }
        }
    }
    /**
     * The getSortedPWs is used to get the sorted list of practical works
     * @param sortingPW is the sorting type of the practical works
     */
    private fun getSortedPWs(sortingPW: SortingPW) {
        getDataJob?.cancel()
        getDataJob = useCases.getPWs(sortingPW).onEach {
            pwStateMSF.emit(pwState.value.copy(pws = it, sortingPW = sortingPW))
        }.launchIn(viewModelScope)
    }
    /**
     * The findPWsByValue is used to get the sorted list of practical works and search by value in the list
     * @param value is the value to be searched
     */
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
    /** The init is used to get the sorted list of practical works by date on creation of the class **/
    init { getSortedPWs(SortingPW.Date(SortType.Descending)) }
}
/**
 * PWEvents is used to send events to the PWListVM to perform actions
 * @property Sort used to sort the data by column
 * @property DeletePW used to delete the practical work from the database
 * @property SearchPW used to search for the practical work
 * @property RestorePW used to restore the deleted practical work
 * @property ToggleSortSection used to switch the visibility of the sorting section
 */
sealed class PWEvents {
    @Stable
    data class Sort(val sortingPW: SortingPW) : PWEvents()
    data class DeletePW(val pw: PracticalWork) : PWEvents()
    data class SearchPW(val value: String) : PWEvents()
    data object RestorePW : PWEvents()
    data object ToggleSortSection : PWEvents()
}