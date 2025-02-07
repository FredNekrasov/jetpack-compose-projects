package com.fredprojects.features.jump.domain.usecases.crud

import com.fredprojects.features.jump.data.repository.JDRepository
import com.fredprojects.features.jump.domain.models.JumpData
import com.fredprojects.features.jump.domain.utils.*

/**
 * UpsertJDUseCase is used to insert or update data in the database
 * @param repository the repository used to insert or update data in the database
 */
class UpsertJDUseCase(
    private val repository: JDRepository
) {
    /**
     * invoke is used to insert or update data in the database
     * @param jumpData the data to be inserted or updated
     * @return the status of the operation
     */
    suspend operator fun invoke(jumpData: JumpData): JumpStatus = when {
        !jumpData.isValid() -> JumpStatus.INCORRECT_DATA
        !jumpData.isCountCorrect() -> JumpStatus.INCORRECT_COUNT
        !jumpData.isDateCorrect() -> JumpStatus.INCORRECT_DATE
        else -> {
            repository.upsert(jumpData)
            JumpStatus.SUCCESS
        }
    }
}