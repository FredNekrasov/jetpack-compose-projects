package com.fredprojects.helloworld.data.mappers

import com.fredprojects.helloworld.data.local.entities.JDEntity
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import java.time.LocalDate

fun JumpData.toEntity() = JDEntity(
    count = count,
    date = date.toString(),
    id = id
)
fun JDEntity.toDomain() = JumpData(
    count = count,
    date = date.toLocalDate() ?: LocalDate.now(),
    id = id
)