package com.fredprojects.data.features.anime.local.dao

import androidx.room.*
import com.fredprojects.data.features.anime.local.entities.AnimeQuoteEntity

/**
 * IAnimeQuotesDao is used to manage data in the database for anime quotes.
 * @see [AnimeQuoteEntity]
 */
@Dao
interface IAnimeQuotesDao {
    /**
     * Insert a new anime quote in the database. If the anime quote already exists, replace it.
     * @param animeQuote the anime quote to be inserted or updated
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(animeQuote: AnimeQuoteEntity)
    /**
     * Get the list of anime quotes from the database
     * @return list of [AnimeQuoteEntity]
     */
    @Query("SELECT * FROM anime_quotes")
    suspend fun getAll(): List<AnimeQuoteEntity>
    /**
     * Delete all anime quotes from the database for the specified anime
     * @param anime the anime to be deleted
     */
    @Query("DELETE FROM anime_quotes WHERE anime = :anime")
    suspend fun deleteByAnime(anime: String)
}