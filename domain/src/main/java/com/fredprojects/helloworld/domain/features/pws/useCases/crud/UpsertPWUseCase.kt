package com.fredprojects.helloworld.domain.features.pws.useCases.crud

import com.fredprojects.helloworld.domain.core.repositories.IRepository
import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import com.fredprojects.helloworld.domain.features.pws.utils.*

/**
 * UpsertPWUseCase is used to insert or update a PracticalWork
 * @param repository is the repository used to insert or update the PracticalWork
 */
class UpsertPWUseCase(
    private val repository: IRepository<PracticalWork>
) {
    /**
     * invoke is used to insert or update a PracticalWork
     * @param pw is the PracticalWork to be inserted or updated
     * @return PWStatus.SUCCESS if the PracticalWork was successfully inserted or updated
     * @return PWStatus.INCORRECT_PW_NAME if the PW name is incorrect
     * @return PWStatus.INCORRECT_STUDENT if the student is incorrect
     * @return PWStatus.INCORRECT_LEVEL if the level is incorrect
     * @return PWStatus.INCORRECT_VARIANT if the variant is incorrect
     * @return PWStatus.INCORRECT_DATE if the date is incorrect
     * @return PWStatus.INCORRECT_MARK if the mark is incorrect
     * @return PWStatus.INCORRECT_IMAGE if the image is incorrect
     */
    suspend operator fun invoke(pw: PracticalWork): PWStatus = when {
        !pw.isPWNameCorrect() -> PWStatus.INCORRECT_PW_NAME
        !pw.isStudentNameCorrect() -> PWStatus.INCORRECT_STUDENT
        !pw.isVariantCorrect() -> PWStatus.INCORRECT_VARIANT
        !pw.isLVLCorrect() -> PWStatus.INCORRECT_LEVEL
        !pw.isDateCorrect() -> PWStatus.INCORRECT_DATE
        !pw.isMarkCorrect() -> PWStatus.INCORRECT_MARK
        !pw.isImageCorrect() -> PWStatus.INCORRECT_IMAGE
        else -> {
            repository.upsert(pw)
            PWStatus.SUCCESS
        }
    }
}