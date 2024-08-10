package com.fredprojects.features.auth.domain.useCases

import com.fredprojects.features.auth.domain.repository.IUserRepository

class DeleteUserUseCase(
    private val repository: IUserRepository
) {
    suspend operator fun invoke(userId: Int) = repository.delete(userId)
}