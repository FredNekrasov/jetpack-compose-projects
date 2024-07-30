package com.fredprojects.helloworld.presentation.core

import android.content.Context
import android.widget.Toast
import com.fredprojects.helloworld.presentation.R
import com.fredprojects.helloworld.domain.core.utils.ActionStatus
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun ActionStatus.getString() = when(this) {
    ActionStatus.LOADING -> R.string.wait
    ActionStatus.SUCCESS -> R.string.success
    ActionStatus.CONNECTION_ERROR -> R.string.connectionError
    ActionStatus.NO_INTERNET -> R.string.noInternet
    ActionStatus.NO_DATA -> R.string.noData
    ActionStatus.SERIALIZATION_ERROR -> R.string.serializationError
    ActionStatus.UNKNOWN -> R.string.unknownError
    ActionStatus.NOTHING -> R.string.empty
}
fun String.toLocalDate() = try {
    if (this.contains(Regex("""^\d{4}-\d{2}-\d{2}$"""))) {
        val localDate = LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        if (localDate <= LocalDate.now()) localDate else null
    } else null
} catch (e: DateTimeParseException) {
    null
}
fun Context.displayToast(messageId: Int) = Toast.makeText(this, getText(messageId), Toast.LENGTH_SHORT).show()