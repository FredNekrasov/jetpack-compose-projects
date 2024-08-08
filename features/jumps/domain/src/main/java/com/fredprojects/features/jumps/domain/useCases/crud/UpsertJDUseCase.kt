package com.fredprojects.features.jumps.domain.useCases.crud

import com.fredprojects.features.jumps.domain.models.JumpData
import com.fredprojects.features.jumps.domain.repositories.IJDRepository
import com.fredprojects.features.jumps.domain.utils.*

/**
 * UpsertJDUseCase is used to insert or update data in the database
 * @param repository the repository used to insert or update data in the database
 */
class UpsertJDUseCase(
    private val repository: IJDRepository
) {
    /**
     * invoke is used to insert or update data in the database
     * @param jumpData the data to be inserted or updated
     * @return the status of the operation
     */
    suspend operator fun invoke(jumpData: JumpData): JumpStatus = when {
        !jumpData.isCountCorrect() -> JumpStatus.INCORRECT_COUNT
        !jumpData.isDateCorrect() -> JumpStatus.INCORRECT_DATE
        !jumpData.isValid() -> JumpStatus.INCORRECT_DATA
        else -> {
            repository.upsert(jumpData)
            JumpStatus.SUCCESS
        }
    }
}