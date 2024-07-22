package com.fredprojects.helloworld.domain.features.jumps.utils

import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import java.time.LocalDate

fun JumpData.isCountCorrect() = count > 0
fun JumpData.isDateCorrect() = date <= LocalDate.now()
fun JumpData.isValid() = isCountCorrect() && isDateCorrect()