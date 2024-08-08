package com.fredprojects.features.jumps.domain.repositories

import com.fredprojects.features.jumps.domain.models.JumpData
import kotlinx.coroutines.flow.Flow

/**
 * IJDRepository represents the interface for the repository.
 * The repository is used to get data from the database.
 */
interface IJDRepository {
    /**
     * upsert data in the database
     * if the data exists in the database, it will be updated
     * if the data does not exist in the database, it will be inserted
     * @param jd the data to be inserted or updated
     */
    suspend fun upsert(jd: JumpData)
    /**
     * delete data in the database
     * @param jd the data to be deleted
     */
    suspend fun delete(jd: JumpData)
    /**
     * getData is used to get data from the database
     * @return the flow of data received from the database
     */
    fun getData(): Flow<List<JumpData>>
}