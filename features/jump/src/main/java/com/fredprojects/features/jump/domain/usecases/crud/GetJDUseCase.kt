package com.fredprojects.features.jump.domain.usecases.crud

import com.fredprojects.features.jump.data.repository.JDRepository
import com.fredprojects.features.jump.domain.utils.SortType
import kotlinx.coroutines.flow.map

/**
 * GetJDUseCase is used to get data from the database
 * @param repository the repository used to get data from the database
 */
class GetJDUseCase(
    private val repository: JDRepository
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