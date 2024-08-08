package com.fredprojects.features.pws.domain.utils

import com.fredprojects.features.pws.domain.models.PracticalWork
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun PracticalWork.isPWNameCorrect() = pwName.isNotBlank()
fun PracticalWork.isStudentNameCorrect() = student.isNotBlank()
fun PracticalWork.isVariantCorrect() = variant > 0
fun PracticalWork.isLVLCorrect() = level > 0
fun PracticalWork.isDateCorrect() = try {
    if (date.contains(Regex("""^\d{2}\.\d{2}\.\d{4}$"""))) {
        val localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        (localDate < LocalDate.now()) && (localDate > LocalDate.now().minusYears(5))
    } else false
} catch (e: DateTimeParseException) {
    false
}
fun PracticalWork.isMarkCorrect() = mark in 1..5
fun PracticalWork.isImageCorrect() = image.isNotBlank()