package com.fredprojects.features.pws.domain.useCases.crud

import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.domain.repository.IPWRepository
import com.fredprojects.features.pws.domain.utils.*
import kotlinx.coroutines.flow.map

/**
 * GetPWUseCase represents the use case for getting the list of practical works.
 * @param repository the repository used to get the list of practical works
 */
class GetPWUseCase(
    private val repository: IPWRepository
) {
    /**
     * Invoke the use case to get the list of practical works.
     * @param sortingPW the sorting type to use
     * @return the list of practical works
     */
    operator fun invoke(sortingPW: SortingPW) = repository.getData().map { pwList ->
        when(sortingPW.sortType) {
            is SortType.Ascending -> {
                when(sortingPW) {
                    is SortingPW.PW -> pwList.sortedBy { it.pwName.lowercase() }
                    is SortingPW.Student -> pwList.sortedBy { it.student.lowercase() }
                    is SortingPW.Variant -> pwList.sortedBy { it.variant }
                    is SortingPW.LVL -> pwList.sortedBy { it.level }
                    is SortingPW.Date -> pwList.sortedBy { it.date }
                    is SortingPW.Mark -> pwList.sortedBy { it.mark }
                }
            }
            is SortType.Descending -> {
                when(sortingPW) {
                    is SortingPW.PW -> pwList.sortedByDescending { it.pwName.lowercase() }
                    is SortingPW.Student -> pwList.sortedByDescending { it.student.lowercase() }
                    is SortingPW.Variant -> pwList.sortedByDescending { it.variant }
                    is SortingPW.LVL -> pwList.sortedByDescending { it.level }
                    is SortingPW.Date -> pwList.sortedByDescending { it.date }
                    is SortingPW.Mark -> pwList.sortedByDescending { it.mark }
                }
            }
        }
    }
    /**
     * Find the practical works that match the given value.
     * @param value the value to search for
     * @param sortingPW the sorting type to use
     * @return the list of practical works that match the given value
     */
    fun find(value: String, sortingPW: SortingPW) = invoke(sortingPW).map { pwList ->
        pwList.filter {
            it.pwName.lowercase().startsWith(value.lowercase()) || it.student.lowercase().startsWith(value.lowercase()) || it.variant.toString().startsWith(value) || it.level.toString().startsWith(value) || it.date.startsWith(value) || it.mark.toString().startsWith(value)
        }
    }
    /**
     * Get the practical work with the given id.
     * @param id the id of the practical work
     * @return the practical work with the given id
     */
    suspend fun getById(id: Int): PracticalWork? = repository.getRecordById(id)
}