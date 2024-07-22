package com.fredprojects.helloworld.data.local.dao

import androidx.room.*
import com.fredprojects.helloworld.data.local.entities.AQEntity

/**
 * IAnimeQuotesDao is used to manage data in the database for anime quotes.
 * @see [AQEntity]
 */
@Dao
interface IAQDao {
    /**
     * Insert a new anime quote in the database. If the anime quote already exists, replace it.
     * @param animeQuote the anime quote to be inserted or updated
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(animeQuote: AQEntity)
    /**
     * Get the list of anime quotes from the database
     * @return list of [AQEntity]
     */
    @Query("SELECT * FROM anime_quotes")
    suspend fun getAll(): List<AQEntity>
    /**
     * Delete all anime quotes from the database for the specified anime
     * @param anime the anime to be deleted
     */
    @Query("DELETE FROM anime_quotes WHERE anime = :anime")
    suspend fun deleteByAnime(anime: String)
}