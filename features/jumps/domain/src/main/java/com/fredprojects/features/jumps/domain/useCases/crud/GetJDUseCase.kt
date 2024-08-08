package com.fredprojects.features.jumps.domain.useCases.crud

import com.fredprojects.features.jumps.domain.repositories.IJDRepository
import com.fredprojects.features.jumps.domain.utils.SortType
import kotlinx.coroutines.flow.map

/**
 * GetJDUseCase is used to get data from the database
 * @param repository the repository used to get data from the database
 */
class GetJDUseCase(
    private val repository: IJDRepository
) {
    /**
     * invoke is used to get data from the database
     * @param sortType the type of sorting
     * @return the flow of data received from the database sorted by date in ascending or descending order
     */
    operator fun invoke(sortType: SortType) = repository.getData().map { jumpDataList ->
        when(sortType) {
            SortType.Ascending -> jumpDataList.sortedBy { it.date }
            SortType.Descending -> jumpDataList.sortedByDescending { it.date }
        }
    }
}