package com.fredprojects.features.clients.domain.astronomy.repository

import com.fredprojects.features.clients.domain.astronomy.models.AstronomyInfo
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
import kotlinx.coroutines.flow.Flow

/**
 * IAstronomyRepository represents the interface for the astronomy repository.
 * The astronomy repository is used to get data from the server and save it in the database.
 */
interface IAstronomyRepository {
    /**
     * getData is used to get data from the server or get data from the cache.
     *
     * @param ra is the right ascension of the location
     * @param dec is the declination of the location
     * @param radius is the radius of the location
     *
     * @return the flow of data received from the server
     */
    fun getData(ra: String, dec: String, radius: Float): Flow<ConnectionStatus<AstronomyInfo>>
}