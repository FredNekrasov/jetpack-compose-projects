package com.fredprojects.helloworld.domain.core.repositories

import kotlinx.coroutines.flow.Flow

/**
 * IRepository represents the interface for the repository.
 * The repository is used to get data from the database.
 * @param M the model of the data received
 */
interface IRepository<M> {
    /**
     * upsert data in the database
     * if the data exists in the database, it will be updated
     * if the data does not exist in the database, it will be inserted
     * @param item the data to be inserted or updated
     */
    suspend fun upsert(item: M)
    /**
     * delete data in the database
     * @param item the data to be deleted
     */
    suspend fun delete(item: M)
    /**
     * getData is used to get data from the database
     * @return the flow of data received from the database
     */
    fun getData(): Flow<List<M>>
    /**
     * getRecord is used to get specific data from the database
     * @param id the id of the data to be retrieved
     * @return the data received from the database
     */
    suspend fun getRecordById(id: Int): M?
}