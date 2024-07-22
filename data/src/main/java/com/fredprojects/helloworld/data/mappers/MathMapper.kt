package com.fredprojects.helloworld.data.mappers

import com.fredprojects.helloworld.data.local.entities.MathEntity
import com.fredprojects.helloworld.domain.features.clients.common.MathModel

fun MathModel.toEntity() = MathEntity(
    expression, result
)
fun MathEntity.toDomain() = MathModel(
    expression, result
)