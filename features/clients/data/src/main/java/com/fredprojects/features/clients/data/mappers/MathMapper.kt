package com.fredprojects.features.clients.data.mappers

import com.fredprojects.core.database.entities.MathEntity
import com.fredprojects.features.clients.domain.math.models.MathModel

internal fun MathModel.toEntity() = MathEntity(
    expression = expression,
    result = result
)
internal fun MathEntity.toDomain() = MathModel(
    expression = expression,
    result = result
)