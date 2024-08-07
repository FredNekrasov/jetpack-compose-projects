package com.fredprojects.features.clients.domain.math.repository

import com.fredprojects.features.clients.domain.math.models.MathModel
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
import kotlinx.coroutines.flow.Flow

/**
 * IMathRepository represents the interface for the client repository.
 * The client repository is used to get data from the server.
 */
interface IMathRepository {
    /**
     * getData is used to get data from the server or get data from the cache.
     * @param expression the data to be sent to the server
     * @return the flow of data received from the server
     */
    fun getData(expression: String): Flow<ConnectionStatus<MathModel>>
}