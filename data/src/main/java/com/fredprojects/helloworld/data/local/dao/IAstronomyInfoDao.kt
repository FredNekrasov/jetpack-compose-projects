package com.fredprojects.helloworld.data.local.dao

import androidx.room.*
import com.fredprojects.helloworld.data.local.entities.AstronomyInfoEntity

/**
 * IAstronomyInfoDao represents the interface for the astronomy info dao.
 * The astronomy info dao is used to get data from the server and save it in the database.
 */
@Dao
interface IAstronomyInfoDao {
    /**
     * insert is used to insert data into the database.
     * OnConflictStrategy.REPLACE is used to replace the data if it already exists.
     * @param listInfo is the list of data to be inserted or updated in the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listInfo: List<AstronomyInfoEntity>)
    /**
     * getAll is used to get data from the database.
     * @param ra is the right ascension of the location
     * @param dec is the declination of the location
     * @param radius is the radius of the search
     * @return list of AstronomyInfoEntity
     */
    @Query("SELECT * FROM astronomy WHERE ra = :ra AND dec = :dec AND radius = :radius")
    suspend fun getAll(ra: String, dec: String, radius: Float): List<AstronomyInfoEntity>
    /**
     * delete is used to delete data from the database.
     * @param listInfo is the list of data to be deleted from the database.
     */
    @Query("DELETE FROM astronomy WHERE name IN(:listInfo)")
    suspend fun delete(listInfo: List<String>)
}