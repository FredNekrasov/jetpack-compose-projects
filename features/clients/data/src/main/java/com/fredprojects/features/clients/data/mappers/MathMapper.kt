package com.fredprojects.features.clients.data.mappers

import com.fredprojects.core.database.entities.MathEntity
import com.fredprojects.features.clients.domain.math.models.MathModel

fun MathModel.toEntity() = MathEntity(
    expression = expression,
    result = result
)
fun MathEntity.toDomain() = MathModel(
    expression = expression,
    result = result
)