package com.fredprojects.features.pws.data.mapper

import com.fredprojects.core.database.entities.PWEntity
import com.fredprojects.features.pws.domain.models.PracticalWork

internal fun PracticalWork.toEntity() = PWEntity(
    pwName = pwName,
    student = student,
    variant = variant,
    level = level,
    date = date,
    mark = mark,
    image = image,
    id = id
)
internal fun PWEntity.toDomain() = PracticalWork(
    pwName = pwName,
    student = student,
    variant = variant,
    level = level,
    date = date,
    mark = mark,
    image = image,
    id = id
)