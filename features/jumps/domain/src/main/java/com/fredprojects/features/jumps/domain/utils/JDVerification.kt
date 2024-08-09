package com.fredprojects.features.jumps.domain.utils

import com.fredprojects.features.jumps.domain.models.JumpData
import java.time.LocalDate

internal fun JumpData.isCountCorrect() = count > 0
internal fun JumpData.isDateCorrect() = date <= LocalDate.now()
internal fun JumpData.isValid() = isCountCorrect() && isDateCorrect()