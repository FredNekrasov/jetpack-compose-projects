package com.fredprojects.features.pws.data.mappers

import com.fredprojects.core.database.entities.PWEntity
import com.fredprojects.features.pws.domain.models.PracticalWork

fun PracticalWork.toEntity() = PWEntity(
    pwName, student, variant, level, date, mark, image, id
)
fun PWEntity.toDomain() = PracticalWork(
    pwName, student, variant, level, date, mark, image, id
)