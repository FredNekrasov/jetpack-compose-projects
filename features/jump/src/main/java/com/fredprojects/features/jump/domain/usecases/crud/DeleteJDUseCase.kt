package com.fredprojects.features.jump.domain.usecases.crud

import com.fredprojects.features.jump.data.repository.JDRepository
import com.fredprojects.features.jump.domain.models.JumpData

/**
 * DeleteJDUseCase is used to delete data in the database
 * @param repository the repository used to delete data
 */
class DeleteJDUseCase(
    private val repository: JDRepository
) {
    /**
     * operator fun invoke is used to delete data in the database
     * @param jumpData the data to be deleted
     */
    suspend operator fun invoke(jumpData: JumpData) = repository.delete(jumpData)
}