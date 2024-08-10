package com.fredprojects.features.auth.domain.useCases

data class UserUseCases(
    val auth: AuthUseCase,
    val upsert: UpsertUserUseCase,
    val delete: DeleteUserUseCase
)