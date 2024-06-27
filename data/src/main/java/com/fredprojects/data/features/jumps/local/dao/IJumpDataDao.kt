package com.fredprojects.data.features.jumps.local.dao

import androidx.room.*
import com.fredprojects.data.features.jumps.local.entities.JDEntity
import kotlinx.coroutines.flow.Flow

/**
 * IJumpDataDao represents the interface for the jump data dao.
 * The jump data dao is used to get data from the server and save it in the database.
 */
@Dao
interface IJumpDataDao {
    /**
     * insert is used to insert data into the database.
     * OnConflictStrategy.REPLACE is used to replace the data if it already exists.
     * @param jdEntity is the data to be inserted or updated in the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jdEntity: JDEntity)
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
    /**
     * getJDE is used to get data from the database.
     * @param id is the id of the data to be retrieved from the database.
     * @return JDEntity
     */
    @Query("SELECT * FROM jumps WHERE id = :id")
    suspend fun getById(id: Int): JDEntity?
}