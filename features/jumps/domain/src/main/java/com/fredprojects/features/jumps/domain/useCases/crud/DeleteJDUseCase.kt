package com.fredprojects.features.jumps.domain.useCases.crud

import com.fredprojects.features.jumps.domain.models.JumpData
import com.fredprojects.features.jumps.domain.repositories.IJDRepository

/**
 * DeleteJDUseCase is used to delete data in the database
 * @param repository the repository used to delete data
 */
class DeleteJDUseCase(
    private val repository: IJDRepository
) {
    /**
     * operator fun invoke is used to delete data in the database
     * @param jumpData the data to be deleted
     */
    suspend operator fun invoke(jumpData: JumpData) = repository.delete(jumpData)
}