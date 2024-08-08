package com.fredprojects.helloworld.domain.features.jumps.useCases

import com.fredprojects.features.jumps.domain.useCases.crud.*

data class JDUseCases(
    val getData: GetJDUseCase,
    val upsert: UpsertJDUseCase,
    val delete: DeleteJDUseCase
)