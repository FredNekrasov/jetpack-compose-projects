package com.fredprojects.features.jumps.data.mappers

import com.fredprojects.core.database.entities.JDEntity
import com.fredprojects.features.jumps.domain.models.JumpData

internal fun JumpData.toEntity() = JDEntity(
    count = count,
    date = date,
    id = id
)
internal fun JDEntity.toDomain() = JumpData(
    count = count,
    date = date,
    id = id
)