package com.fredprojects.helloworld.domain.features.jumps.models

import java.time.LocalDate

/**
 * JumpData is used to store data of the jumps.
 * @param count the number of jumps
 * @param date the date of the training
 * @param id the identifier of the record
 */
data class JumpData(
    val count: Int,
    val date: LocalDate = LocalDate.now(),
    val id: Int? = null
)