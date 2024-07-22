package com.fredprojects.helloworld.data.mappers

import com.fredprojects.helloworld.data.local.entities.AstronomyInfoEntity
import com.fredprojects.helloworld.data.remote.dto.AstronomyInfoDto
import com.fredprojects.helloworld.domain.features.clients.astronomy.models.AstronomyInfo

fun AstronomyInfoEntity.toDomain() = AstronomyInfo(
    dec, name, references, ra, radius
)
fun AstronomyInfoDto.toEntity(ra: String, name: String, dec: String, radius: Float) = AstronomyInfoEntity(
    dec, name, references.joinToString("\n"), ra, radius
)