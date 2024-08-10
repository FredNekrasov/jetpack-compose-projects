package com.fredprojects.features.clients.data.remote.services

import com.fredprojects.features.clients.data.remote.dto.BBResponseDto
import retrofit2.http.GET

/**
 * IBBService is used to work with the Bybit API.
 */
interface IBBService {
    /**
     * getProductInfo is used to get data from the server.
     * @return list of [BBResponseDto]
     */
    @GET("/v5/announcements/index?locale=en-US&limit=10")
    suspend fun getProductInfo(): BBResponseDto?
    companion object {
        const val BASE_URL = "https://api.bybit.com"
    }
}