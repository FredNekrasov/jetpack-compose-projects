package com.fredprojects.helloworld.domain.features.pws.useCases.crud

import com.fredprojects.helloworld.domain.core.repositories.IRepository
import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork

/**
 * DeletePWUseCase is used to delete data in the database
 * @param repository the repository used to delete data
 */
class DeletePWUseCase(
    private val repository: IRepository<PracticalWork>
) {
    /**
     * delete data in the database
     * @param pw the data to be deleted
     */
    suspend operator fun invoke(pw: PracticalWork) = repository.delete(pw)
}