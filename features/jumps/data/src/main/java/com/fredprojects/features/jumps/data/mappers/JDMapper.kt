package com.fredprojects.features.jumps.data.mappers

import com.fredprojects.core.database.entities.JDEntity
import com.fredprojects.features.jumps.domain.models.JumpData
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
/**
 * toLocalDate is used to convert a string to a LocalDate in the format "yyyy-MM-dd".
 * if the string is not in the format "yyyy-MM-dd" it will return null.
 */
private fun String.toLocalDate() = try {
    if (this.contains(Regex("""^\d{4}-\d{2}-\d{2}$"""))) {
        val localDate = LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        if (localDate < LocalDate.now()) localDate else null
    } else null
} catch (e: DateTimeParseException) {
    null
}