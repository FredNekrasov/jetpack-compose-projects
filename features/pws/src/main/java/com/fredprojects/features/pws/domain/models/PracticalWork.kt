package com.fredprojects.features.pws.domain.models

data class PracticalWork(
    val pwName: String,
    val student: String,
    val variant: Int,
    val level: Int,
    val date: String,
    val mark: Int,
    val image: String,
    val id: Int? = null
)