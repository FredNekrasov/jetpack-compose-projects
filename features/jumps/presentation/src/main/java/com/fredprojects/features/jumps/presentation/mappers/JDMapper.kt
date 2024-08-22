package com.fredprojects.features.jumps.presentation.mappers

import com.fredprojects.features.jumps.domain.models.JumpData
import com.fredprojects.features.jumps.presentation.models.JDPModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

internal fun JumpData.toPresentation() = JDPModel(
    count, date.toString(), id
)
internal fun JDPModel.toDomain() = JumpData(
    count ?: 0, date.toLocalDate() ?: LocalDate.now(), id
)
private fun String.toLocalDate() = try {
    if (this.contains(Regex("""^\d{4}-\d{2}-\d{2}$"""))) {
        val localDate = LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        if (localDate <= LocalDate.now()) localDate else null
    } else null
} catch (e: DateTimeParseException) {
    null
}