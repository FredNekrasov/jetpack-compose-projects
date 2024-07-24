package com.fredprojects.helloworld.presentation.features.pws.vm

import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import com.fredprojects.helloworld.domain.features.pws.utils.PWStatus

data class UpsertPWState(
    val pw: PracticalWork? = null,
    val status: PWStatus = PWStatus.SUCCESS
)