package com.fredprojects.helloworld.data.mappers

import com.fredprojects.helloworld.data.local.entities.AQEntity
import com.fredprojects.helloworld.domain.features.clients.common.AnimeQuote

fun AnimeQuote.toEntity() = AQEntity(
    anime.lowercase(), character, quote
)
fun AQEntity.toDomain() = AnimeQuote(
    anime, character, quote
)