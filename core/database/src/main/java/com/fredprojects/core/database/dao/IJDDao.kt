package com.fredprojects.core.database.dao

import androidx.room.*
import com.fredprojects.core.database.entities.JDEntity
import kotlinx.coroutines.flow.Flow

/**
 * IJumpDataDao represents the interface for the jump data dao.
 * The jump data dao is used to get data from the server and save it in the database.
 */
@Dao
interface IJDDao {
    /**
     * upsert is used to update the data if it already exists.
     * @param jdEntity is the data to be inserted or updated in the database.
     */
    @Upsert
    suspend fun upsert(jdEntity: JDEntity)
    /**
     * delete is used to delete data from the database.
     * @param jdEntity is the data to be deleted from the database.
     */
    @Delete
    suspend fun delete(jdEntity: JDEntity)
    /**
     * getAll is used to get all data from the database.
     * @return list of JDEntity
     */
    @Query("SELECT * FROM jumps")
    fun getAll(): Flow<List<JDEntity>>
}