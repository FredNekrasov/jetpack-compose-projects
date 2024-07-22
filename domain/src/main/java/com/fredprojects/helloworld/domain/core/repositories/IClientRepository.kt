package com.fredprojects.helloworld.domain.core.repositories

import com.fredprojects.helloworld.domain.core.utils.ConnectionStatus
import kotlinx.coroutines.flow.Flow

/**
 * IClientRepository represents the interface for the client repository.
 * The client repository is used to get data from the server.
 * @param M the model of the data received
 */
interface IClientRepository<M> {
    /**
     * getData is used to get data from the server or get data from the cache.
     * @param data the data to be sent to the server
     * @return the flow of data received from the server
     */
    fun getData(data: String): Flow<ConnectionStatus<M>>
}