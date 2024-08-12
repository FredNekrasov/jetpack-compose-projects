package com.fredprojects.features.pws.presentation.models

import androidx.compose.runtime.Stable

/**
 * Practical Work Presentation Model is used to store data of the practical works.
 * @param pwName the name of the practical work
 * @param student the name of the student
 * @param variant the variant of the practical work
 * @param level the level of the practical work
 * @param date the date of the practical work
 * @param mark the mark of the practical work
 * @param image the image of the practical work
 * @param id the identifier of the record
 */
@Stable
data class PWPModel(
    val pwName: String,
    val student: String,
    val variant: Int,
    val level: Int,
    val date: String,
    val mark: Int,
    val image: String,
    val id: Int? = null
)