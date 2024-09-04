package com.fredprojects.features.jumps.presentation.mappers

import com.fredprojects.features.jumps.domain.models.JumpData
import com.fredprojects.features.jumps.presentation.models.JDPModel

internal fun JumpData.toPresentation() = JDPModel(
    count, date, id
)
internal fun JDPModel.toDomain() = JumpData(
    count ?: 0, date, id
)