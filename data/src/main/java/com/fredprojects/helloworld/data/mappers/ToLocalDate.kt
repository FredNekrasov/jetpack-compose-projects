package com.fredprojects.helloworld.data.mappers

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun String.toLocalDate() = try {
    if (this.contains(Regex("""^\d{4}-\d{2}-\d{2}$"""))) {
        val localDate = LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        if (localDate < LocalDate.now()) localDate else null
    } else null
} catch (e: DateTimeParseException) {
    null
}