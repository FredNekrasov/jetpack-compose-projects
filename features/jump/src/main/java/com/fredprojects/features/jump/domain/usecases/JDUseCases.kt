package com.fredprojects.features.jump.domain.usecases

import com.fredprojects.features.jump.domain.usecases.crud.*

data class JDUseCases(
    val getData: GetJDUseCase,
    val upsert: UpsertJDUseCase,
    val delete: DeleteJDUseCase
)