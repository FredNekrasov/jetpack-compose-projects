package com.fredprojects.helloworld.domain.features.jumps.useCases.crud

import com.fredprojects.helloworld.domain.core.repositories.IRepository
import com.fredprojects.helloworld.domain.core.utils.SortType
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import kotlinx.coroutines.flow.map

/**
 * GetJDUseCase is used to get data from the database
 * @param repository the repository used to get data from the database
 */
class GetJDUseCase(
    private val repository: IRepository<JumpData>
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
    /**
     * getById is used to get data from the database
     * @param id the id of the data to be retrieved
     * @return the data received from the database
     */
    suspend fun getById(id: Int): JumpData? = repository.getRecordById(id)
}