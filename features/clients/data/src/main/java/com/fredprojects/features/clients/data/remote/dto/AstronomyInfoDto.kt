package com.fredprojects.features.clients.data.remote.dto

/**
 * AstronomyInfoDto is used to parse the JSON data returned from the API
 */
data class AstronomyInfoDto(
    val references: List<String>,
    val name: List<String>
)