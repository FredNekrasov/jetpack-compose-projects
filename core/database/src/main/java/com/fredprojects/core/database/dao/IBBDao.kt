package com.fredprojects.core.database.dao

import androidx.room.*
import com.fredprojects.core.database.entities.BBInfoEntity

/**
 * IBBDao represents the interface for the bybit db dao.
 * The bybit db dao is used to get data from the server and save it in the database.
 */
@Dao
interface IBBDao {
    /**
     * upsert is used to insert data into the database or update it if it already exists.
     * @param bbInfoEntity is the data to be inserted or updated in the database.
     */
    @Upsert
    suspend fun upsert(bbInfoEntity: BBInfoEntity)
    /**
     * getAll is used to get data from the database.
     * @return list of BBInfoEntity
     */
    @Query("SELECT * FROM bybit")
    suspend fun getAll(): List<BBInfoEntity>
    /**
     * delete is used to delete data from the database.
     * @param title is the title of the data to be deleted
     */
    @Query("DELETE FROM bybit WHERE title = :title")
    suspend fun delete(title: String)
}