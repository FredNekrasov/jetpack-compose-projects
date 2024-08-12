package com.fredprojects.features.auth.presentation.mappers

import com.fredprojects.features.auth.domain.models.User
import com.fredprojects.features.auth.presentation.models.UDPModel

internal fun User.toPresentation() = UDPModel(
    login = login,
    password = password,
    email = email,
    name = name,
    surname = surname,
    id = id
)
internal fun UDPModel.toDomain() = User(
    login = login,
    password = password,
    email = email,
    name = name,
    surname = surname,
    id = id
)