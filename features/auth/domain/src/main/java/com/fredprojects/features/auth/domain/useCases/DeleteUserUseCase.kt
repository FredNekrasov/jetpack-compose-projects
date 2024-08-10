package com.fredprojects.features.auth.domain.useCases

import com.fredprojects.features.auth.domain.repository.IUserRepository

/**
 * DeleteUserUseCase is used to delete data in the database
 * @param repository the repository used to delete data
 */
class DeleteUserUseCase(
    private val repository: IUserRepository
) {
    /**
     * invoke is used to delete data in the database
     * @param userId the id of the user to be deleted
     */
    suspend operator fun invoke(userId: Int) = repository.delete(userId)
}