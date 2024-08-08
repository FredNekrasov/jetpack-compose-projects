package com.fredprojects.features.pws.presentation.vm

import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.domain.utils.PWStatus

/**
 * UpsertPWState is used to insert or update a PracticalWork and its status after the operation is completed
 * @param pw the PracticalWork to be inserted or updated
 * @param status the status of the practical work to be inserted or updated
 */
data class UpsertPWState(
    val pw: PracticalWork? = null,
    val status: PWStatus = PWStatus.NOTHING
)