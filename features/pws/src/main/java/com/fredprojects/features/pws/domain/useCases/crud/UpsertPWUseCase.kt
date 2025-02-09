package com.fredprojects.features.pws.domain.useCases.crud

import com.fredprojects.features.pws.data.repository.PWRepository
import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.domain.utils.*

class UpsertPWUseCase(
    private val repository: PWRepository
) {
    /**
     * invoke is used to insert or update a PracticalWork
     * @param pw is the PracticalWork to be inserted or updated
     * @return the status of the operation
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