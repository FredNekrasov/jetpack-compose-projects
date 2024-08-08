package com.fredprojects.features.pws.domain.repository

import com.fredprojects.features.pws.domain.models.PracticalWork
import kotlinx.coroutines.flow.Flow

/**
 * IPWRepository represents the interface for the repository.
 * The repository is used to write and read data from the database.
 */
interface IPWRepository {
    /**
     * upsert data in the database
     * if the data exists in the database, it will be updated
     * if the data does not exist in the database, it will be inserted
     * @param pw the data to be inserted or updated
     */
    suspend fun upsert(pw: PracticalWork)
    /**
     * delete data in the database
     * @param pw the data to be deleted
     */
    suspend fun delete(pw: PracticalWork)
    /**
     * getData is used to get data from the database
     * @return the flow of data received from the database
     */
    fun getData(): Flow<List<PracticalWork>>
    /**
     * getRecord is used to get specific data from the database
     * @param id the id of the data to be retrieved
     * @return the data received from the database
     */
    suspend fun getRecordById(id: Int): PracticalWork?
}