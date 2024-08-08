package com.fredprojects.features.jumps.domain.utils

import com.fredprojects.features.jumps.domain.models.JumpData
import java.time.LocalDate

fun JumpData.isCountCorrect() = count > 0
fun JumpData.isDateCorrect() = date <= LocalDate.now()
fun JumpData.isValid() = isCountCorrect() && isDateCorrect()