package com.fredprojects.helloworld.data.mappers

import com.fredprojects.helloworld.data.local.entities.PWEntity
import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork

fun PracticalWork.toEntity() = PWEntity(
    pwName, student, variant, level, date, mark, image, id
)
fun PWEntity.toDomain() = PracticalWork(
    pwName, student, variant, level, date, mark, image, id
)