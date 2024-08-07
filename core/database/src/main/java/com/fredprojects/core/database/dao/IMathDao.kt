package com.fredprojects.core.database.dao

import androidx.room.*
import com.fredprojects.core.database.entities.MathEntity

/**
 * IMathDao is an interface that defines the methods that can be used to access and manipulate data in the database.
 */
@Dao
interface IMathDao {
    /**
     * Insert is used to insert data into the database.
     * OnConflictStrategy.REPLACE is used to replace the data if it already exists.
     * @param math the data to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(math: MathEntity)
    /**
     * Delete is used to delete data from the database by its expression.
     * @param expression the expression of the data to be deleted
     */
    @Query("DELETE FROM math WHERE expression = :expression")
    suspend fun deleteByExpression(expression: String)
    /**
     * GetByExpression is used to get data from the database by its expression.
     * @param expression the expression of the data to be retrieved
     * @return the data received from the database
     */
    @Query("SELECT * FROM math WHERE expression = :expression")
    suspend fun getByExpression(expression: String): MathEntity?
    /**
     * GetAll is used to get all data from the database.
     * @return the list of data received from the database
     */
    @Query("SELECT * FROM math")
    suspend fun getAll(): List<MathEntity>
}