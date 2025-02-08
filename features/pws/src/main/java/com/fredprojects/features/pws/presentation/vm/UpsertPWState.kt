package com.fredprojects.features.pws.presentation.vm

import androidx.compose.runtime.Stable
import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.domain.utils.PWStatus

@Stable
data class UpsertPWState(
    val pw: PracticalWork? = null,
    val status: PWStatus = PWStatus.NOTHING
)