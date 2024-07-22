package com.fredprojects.helloworld.data.repositories

import com.fredprojects.helloworld.data.local.dao.IAQDao
import com.fredprojects.helloworld.data.mappers.toDomain
import com.fredprojects.helloworld.data.mappers.toEntity
import com.fredprojects.helloworld.data.remote.services.IAQService
import com.fredprojects.helloworld.domain.core.repositories.IClientRepository
import com.fredprojects.helloworld.domain.core.utils.ActionStatus.*
import com.fredprojects.helloworld.domain.core.utils.ConnectionStatus
import com.fredprojects.helloworld.domain.features.clients.common.AnimeQuote
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException

/**
 * AnimeQuote repository for processing API requests and responses
 * @param dao the local database access object for AnimeQuote. See [IAQDao]
 * @param api a remote API access object for making API requests. See [IAQService]
 */
class AQRepository(
    private val dao: IAQDao,
    private val api: IAQService
) : IClientRepository<AnimeQuote> {
    /**
     * Get data from the API and store it in the local database
     * @param data the name of the anime used as a parameter for the API request
     * @return Flow<ConnectionStatus<AnimeQuote>>
     * @see ConnectionStatus
     */
    override fun getData(data: String): Flow<ConnectionStatus<AnimeQuote>> = flow {
        val astronomyInfoList = dao.getAll().map { it.toDomain() }
        emit(ConnectionStatus.Loading(astronomyInfoList))
        val remoteData = api.getAnimeQuotes(data)
        if(remoteData != null) {
            dao.deleteByAnime(data.lowercase())
            remoteData.forEach { dao.insert(it.toEntity()) }
            emit(ConnectionStatus.Success(dao.getAll().map { it.toDomain() }))
        } else emit(ConnectionStatus.Error(astronomyInfoList, NO_DATA))
    }.catch { ex ->
        val astronomyInfoList = dao.getAll().map { it.toDomain() }
        when(ex) {
            is JsonSyntaxException -> emit(ConnectionStatus.Error(astronomyInfoList, SERIALIZATION_ERROR))
            is HttpException -> emit(ConnectionStatus.Error(astronomyInfoList, CONNECTION_ERROR))
            is IOException -> emit(ConnectionStatus.Error(astronomyInfoList, NO_INTERNET))
            else -> emit(ConnectionStatus.Error(astronomyInfoList, UNKNOWN))
        }
    }
}