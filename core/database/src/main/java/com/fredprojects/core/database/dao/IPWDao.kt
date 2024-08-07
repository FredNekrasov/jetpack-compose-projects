package com.fredprojects.core.database.dao

import androidx.room.*
import com.fredprojects.core.database.entities.PWEntity
import kotlinx.coroutines.flow.Flow

/**
 * IPracticalWorkDao represents the interface for the practical work dao.
 * The practical work dao is used to get data from the server and save it in the database.
 */
@Dao
interface IPWDao {
    /**
     * upsert is used to update the data if it already exists in the database or insert it if it doesn't exist.
     * @param pwEntity the practical work to be inserted or updated in the database.
     */
    @Upsert
    suspend fun upsert(pwEntity: PWEntity)
    /**
     * delete is used to delete data from the database.
     * @param pwEntity the practical work to be deleted from the database.
     */
    @Delete
    suspend fun delete(pwEntity: PWEntity)
    /**
     * getAll is used to get the list of practical works from the database.
     * @return the list of practical works
     */
    @Query("SELECT * FROM practical_works")
    fun getAll(): Flow<List<PWEntity>>
    /**
     * getById is used to get the practical work with the given id from the database.
     * @param id the id of the practical work
     * @return the practical work with the given id
     */
    @Query("SELECT * FROM practical_works WHERE id = :id")
    suspend fun getById(id: Int): PWEntity?
}