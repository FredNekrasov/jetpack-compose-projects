package com.fredprojects.helloworld.data.mappers

import com.fredprojects.helloworld.data.local.entities.JDEntity
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun JumpData.toEntity() = JDEntity(
    count = count,
    date = date.toString(),
    id = id
)
fun JDEntity.toDomain() = JumpData(
    count = count,
    date = date.toLocalDate() ?: LocalDate.now(),
    id = id
)
private fun String.toLocalDate() = try {
    if (this.contains(Regex("""^\d{4}-\d{2}-\d{2}$"""))) {
        val localDate = LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        if (localDate < LocalDate.now()) localDate else null
    } else null
} catch (e: DateTimeParseException) {
    null
}