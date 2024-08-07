package com.fredprojects.features.clients.data.remote.services

import com.fredprojects.features.clients.data.remote.dto.AstronomyInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * IAstronomyInfoService represents the interface for the astronomy info service.
 * The astronomy info service is used to get data from the server.
 */
interface IAstronomyInfoService {
    /**
     * getAstronomyInfo is used to get data from the server.
     * @param ra is the right ascension of the location
     * @param dec is the declination of the location
     * @param radius is the radius of the search
     * @return list of [AstronomyInfoDto]
     */
    @GET("/catalog/")
    suspend fun getAstronomyInfo(
        @Query("ra") ra: String,
        @Query("dec") dec: String,
        @Query("radius") radius: Float
    ): Map<String, AstronomyInfoDto>?
    companion object {
        const val BASE_URL = "https://api.astrocats.space"
    }
}