package com.fredprojects.helloworld.data.remote.services

import com.fredprojects.helloworld.domain.features.clients.common.AnimeQuote
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * IAnimeQuotesService is used to work with the Anime Quotes API.
 */
interface IAQService {
    /**
     * Get the list of anime quotes from the API.
     * @param title the name of the anime
     * @return list of [AnimeQuote]
     */
    @GET("/api/quotes/anime/")
    suspend fun getAnimeQuotes(@Query("title") title: String): List<AnimeQuote>?
    companion object {
        const val BASE_URL = "https://animechan.xyz"
    }
}