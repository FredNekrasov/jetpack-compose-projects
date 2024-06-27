package com.fredprojects.data.features.anime.remote.service

import com.fredprojects.data.features.anime.remote.dto.AnimeQuoteDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * IAnimeQuotesService is used to work with the Anime Quotes API.
 */
interface IAnimeQuotesService {
    /**
     * Get the list of anime quotes from the API.
     * @param title the name of the anime
     * @return list of [AnimeQuoteDto]
     */
    @GET("/api/quotes/anime/")
    fun getAnimeQuotes(@Query("title") title: String): Call<List<AnimeQuoteDto>>
    companion object {
        const val BASE_URL = "https://animechan.xyz"
    }
}