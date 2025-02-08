package com.fredprojects.features.auth.data.mapper

import com.fredprojects.core.database.entities.UserEntity
import com.fredprojects.features.auth.domain.models.User

internal fun UserEntity.toDomain() = User(
    login = login,
    password = password,
    email = email,
    name = name,
    surname = surname,
    id = id
)
internal fun User.toEntity() = UserEntity(
    login = login,
    password = password,
    email = email,
    name = name,
    surname = surname,
    id = id
)