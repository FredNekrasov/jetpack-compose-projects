package com.fredprojects.helloworld.presentation.features.pws.vm

import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import com.fredprojects.helloworld.domain.features.pws.utils.PWStatus

/**
 * UpsertPWState is used to insert or update a PracticalWork and its status after the operation is completed
 * @param pw the PracticalWork to be inserted or updated
 * @param status the status of the practical work to be inserted or updated
 */
data class UpsertPWState(
    val pw: PracticalWork? = null,
    val status: PWStatus = PWStatus.SUCCESS
)