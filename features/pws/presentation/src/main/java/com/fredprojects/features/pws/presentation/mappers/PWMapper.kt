package com.fredprojects.features.pws.presentation.mappers

import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.presentation.models.PWPModel

internal fun PracticalWork.toPresentation() = PWPModel(
    pwName, student, variant, level, date, mark, image, id
)
internal fun PWPModel.toDomain() = PracticalWork(
    pwName, student, variant, level, date, mark, image, id
)