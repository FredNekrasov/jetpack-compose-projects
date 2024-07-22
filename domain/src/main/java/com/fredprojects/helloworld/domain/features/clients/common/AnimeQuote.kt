package com.fredprojects.helloworld.domain.features.clients.common

/**
 * AnimeQuote is used to store data of the anime quotes.
 * @param anime the name of the anime
 * @param character the name of the character
 * @param quote the quote of the character
 * Разработайте клиент https://github.com/RocktimSaikia/anime-chan
 * с сохранением загруженной информации на мобильном устройстве и загрузкой информации в отдельных потоках.
 * По данному аниме выведите цитаты из него.
 */
data class AnimeQuote(
    val anime: String,
    val character: String,
    val quote: String
)