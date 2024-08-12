package com.fredprojects.features.jumps.presentation.models

import androidx.compose.runtime.Stable
import java.time.LocalDate

/**
 * JumpDataPresentationModel is used to store data of the jumps.
 * @param count the number of jumps
 * @param date the date of the training
 * @param id the identifier of the record
 */
@Stable
data class JDPModel(
    val count: Int,
    val date: LocalDate = LocalDate.now(),
    val id: Int? = null
)