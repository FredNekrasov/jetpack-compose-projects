package com.fredprojects.helloworld.domain.features.clients.astronomy.models

/**
 * AstronomyInfo is used to store data of the astronomy information.
 *
 * @param dec the declination of the object
 * @param name the name of the object
 * @param references the references of the object
 * @param ra the right ascension of the object
 * @param radius the radius of the object
 */
data class AstronomyInfo(
    val dec: String,
    val name: String,
    val references: String,
    val ra: String,
    val radius: Float
)