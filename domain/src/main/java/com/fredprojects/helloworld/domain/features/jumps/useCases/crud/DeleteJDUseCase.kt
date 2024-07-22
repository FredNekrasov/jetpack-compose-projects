package com.fredprojects.helloworld.domain.features.jumps.useCases.crud

import com.fredprojects.helloworld.domain.core.repositories.IRepository
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData

/**
 * DeleteJDUseCase is used to delete data in the database
 * @param repository the repository used to delete data
 */
class DeleteJDUseCase(
    private val repository: IRepository<JumpData>
) {
    /**
     * operator fun invoke is used to delete data in the database
     * @param jumpData the data to be deleted
     */
    suspend operator fun invoke(jumpData: JumpData) = repository.delete(jumpData)
}