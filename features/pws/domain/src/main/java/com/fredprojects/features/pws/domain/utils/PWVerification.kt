package com.fredprojects.features.pws.domain.utils

import com.fredprojects.features.pws.domain.models.PracticalWork
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

internal fun PracticalWork.isPWNameCorrect() = pwName.isNotBlank()
internal fun PracticalWork.isStudentNameCorrect() = student.isNotBlank()
internal fun PracticalWork.isVariantCorrect() = variant > 0
internal fun PracticalWork.isLVLCorrect() = level > 0
internal fun PracticalWork.isDateCorrect() = try {
    if (date.contains(Regex("""^\d{2}\.\d{2}\.\d{4}$"""))) {
        val localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        (localDate < LocalDate.now()) && (localDate > LocalDate.now().minusYears(5))
    } else false
} catch (e: DateTimeParseException) {
    false
}
internal fun PracticalWork.isMarkCorrect() = mark in 1..5
internal fun PracticalWork.isImageCorrect() = image.isNotBlank()