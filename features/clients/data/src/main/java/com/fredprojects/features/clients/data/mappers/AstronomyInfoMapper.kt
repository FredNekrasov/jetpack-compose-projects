package com.fredprojects.features.clients.data.mappers

import com.fredprojects.core.database.entities.AstronomyInfoEntity
import com.fredprojects.features.clients.domain.astronomy.models.AstronomyInfo
import com.fredprojects.features.clients.data.remote.dto.AstronomyInfoDto

fun AstronomyInfoEntity.toDomain() = AstronomyInfo(
    dec, name, references, ra, radius
)
fun AstronomyInfoDto.toEntity(ra: String, name: String, dec: String, radius: Float) = AstronomyInfoEntity(
    dec, name, references.joinToString("\n"), ra, radius
)