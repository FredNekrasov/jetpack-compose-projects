package com.fredprojects.helloworld.domain.features.jumps.useCases.crud

import com.fredprojects.helloworld.domain.core.repositories.IRepository
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import com.fredprojects.helloworld.domain.features.jumps.utils.*

/**
 * UpsertJDUseCase is used to insert or update data in the database
 * @param repository the repository used to insert or update data in the database
 */
class UpsertJDUseCase(
    private val repository: IRepository<JumpData>
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