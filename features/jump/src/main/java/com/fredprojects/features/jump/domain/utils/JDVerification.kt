package com.fredprojects.features.jump.domain.utils

import com.fredprojects.features.jump.domain.models.JumpData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

internal fun JumpData.isCountCorrect() = count > 0
internal fun JumpData.isDateCorrect() = try {
    if (date.contains(Regex("""^\d{4}-\d{2}-\d{2}$"""))) {
        val localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        localDate <= LocalDate.now()
    } else false
} catch (e: DateTimeParseException) {
    false
}
internal fun JumpData.isValid() = isCountCorrect() || isDateCorrect()
