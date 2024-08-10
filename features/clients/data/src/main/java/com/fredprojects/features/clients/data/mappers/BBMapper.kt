package com.fredprojects.features.clients.data.mappers

import com.fredprojects.core.database.entities.BBInfoEntity
import com.fredprojects.features.clients.data.remote.dto.BBInfoDto
import com.fredprojects.features.clients.domain.bybit.models.BBInfo
import com.fredprojects.features.clients.domain.bybit.models.BBType
import com.google.gson.Gson
import java.lang.reflect.Type
import java.time.*

class BBTypeParser(private val gson: Gson) {
    fun fromJsonToBBType(json: String, bbType: Class<BBType>): BBType = gson.fromJson(json, bbType)
    fun fromBBTypeToJson(bbType: BBType, type: Type): String = gson.toJson(bbType, type)
}
internal fun BBInfoDto.toDomain() = BBInfo(
    title = title,
    type = type,
    dateTime = dateTimestamp.toLocalDateTime().toString(),
    description = description,
    endDateTime = endDateTimestamp.toLocalDateTime().toString(),
    startDateTime = startDateTimestamp.toLocalDateTime().toString(),
    tags = tags,
    url = url
)
internal fun BBInfoDto.toEntity(bbTypeParser: BBTypeParser) = BBInfoEntity(
    title = title,
    type = bbTypeParser.fromBBTypeToJson(type, BBType::class.java),
    dateTime = dateTimestamp.toLocalDateTime().toString(),
    description = description,
    endDateTime = endDateTimestamp.toLocalDateTime().toString(),
    startDateTime = startDateTimestamp.toLocalDateTime().toString(),
    tags = tags.joinToString(", "),
    url = url
)
internal fun BBInfoEntity.toDomain(bbTypeParser: BBTypeParser) = BBInfo(
    title = title,
    type = bbTypeParser.fromJsonToBBType(type, BBType::class.java),
    dateTime = dateTime,
    description = description,
    endDateTime = endDateTime,
    startDateTime = startDateTime,
    tags = tags.split(", "),
    url = url,
    favorite = favorite,
    id = id
)
internal fun BBInfo.toEntity(bbTypeParser: BBTypeParser) = BBInfoEntity(
    title = title,
    type = bbTypeParser.fromBBTypeToJson(type, BBType::class.java),
    dateTime = dateTime,
    description = description,
    endDateTime = endDateTime,
    startDateTime = startDateTime,
    tags = tags.joinToString(", "),
    url = url,
    favorite = favorite,
    id = id
)
private fun Long.toLocalDateTime(): LocalDateTime = Instant.ofEpochSecond(this).atZone(ZoneId.systemDefault()).toLocalDateTime()