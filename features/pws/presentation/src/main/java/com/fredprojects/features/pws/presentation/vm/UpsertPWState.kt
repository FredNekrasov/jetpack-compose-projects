package com.fredprojects.features.pws.presentation.vm

import androidx.compose.runtime.Stable
import com.fredprojects.features.pws.domain.utils.PWStatus
import com.fredprojects.features.pws.presentation.models.PWPModel

/**
 * UpsertPWState is used to insert or update a PracticalWork and its status after the operation is completed
 * @param pw the PracticalWork to be inserted or updated
 * @param status the status of the practical work to be inserted or updated
 */
@Stable
data class UpsertPWState(
    val pw: PWPModel? = null,
    val status: PWStatus = PWStatus.NOTHING
)