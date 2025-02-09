package com.fredprojects.features.pws.domain.useCases.crud

import com.fredprojects.features.pws.data.repository.PWRepository
import com.fredprojects.features.pws.domain.models.PracticalWork

class DeletePWUseCase(
    private val repository: PWRepository
) {
    suspend operator fun invoke(pw: PracticalWork) = repository.delete(pw)
}