package com.fredprojects.domain.features.wapi.common

/**
 * AnimeQuote is used to store data of the anime quotes.
 *
 * @param anime the name of the anime
 * @param character the name of the character
 * @param quote the quote of the character
 */
data class AnimeQuote(
    val anime: String,
    val character: String,
    val quote: String
)