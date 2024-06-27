package com.fredprojects.domain.features.pws.useCases

import com.fredprojects.domain.features.pws.useCases.crud.*

data class PWUseCases(
    val getPWs: GetPWUseCase,
    val upsert: UpsertPWUseCase,
    val delete: DeletePWUseCase
)