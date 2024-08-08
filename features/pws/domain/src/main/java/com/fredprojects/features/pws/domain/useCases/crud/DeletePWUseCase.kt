package com.fredprojects.features.pws.domain.useCases.crud

import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.domain.repository.IPWRepository

/**
 * DeletePWUseCase is used to delete data in the database
 * @param repository the repository used to delete data
 */
class DeletePWUseCase(
    private val repository: IPWRepository
) {
    /**
     * delete data in the database
     * @param pw the data to be deleted
     */
    suspend operator fun invoke(pw: PracticalWork) = repository.delete(pw)
}