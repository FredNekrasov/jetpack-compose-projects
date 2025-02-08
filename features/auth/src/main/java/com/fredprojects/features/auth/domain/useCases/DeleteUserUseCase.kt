package com.fredprojects.features.auth.domain.useCases

import com.fredprojects.features.auth.data.repository.UserRepository

/**
 * DeleteUserUseCase is used to delete data in the database
 * @param repository the repository used to delete data
 */
class DeleteUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userId: Int) = repository.delete(userId)
}