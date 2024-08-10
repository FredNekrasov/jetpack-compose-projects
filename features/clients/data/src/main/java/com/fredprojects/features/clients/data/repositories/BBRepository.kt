package com.fredprojects.features.clients.data.repositories

import com.fredprojects.core.database.dao.IBBDao
import com.fredprojects.features.clients.data.mappers.*
import com.fredprojects.features.clients.data.remote.services.IBBService
import com.fredprojects.features.clients.domain.bybit.models.BBInfo
import com.fredprojects.features.clients.domain.bybit.repository.IBBRepository
import com.fredprojects.features.clients.domain.utils.ActionStatus.*
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
import com.fredprojects.features.clients.domain.utils.ConnectionStatus.*
import com.google.gson.JsonParseException
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException

/**
 * ByBitRepository is used to get data from the database and from the remote server
 *
 * @param dao the dao used to get data from the database
 * @param api the api used to get data from the remote server
 * @param bbTypeParser the parser used to convert data from the database to the domain model
 *
 * The repository uses the following strategy to get data:
 * - If there is no internet connection, it gets data from the local database
 * - If there is internet connection, it tries to get data from the remote server. If it fails, it tries to get data from the local database.
 */
class BBRepository(
    private val dao: IBBDao,
    private val api: IBBService,
    private val bbTypeParser: BBTypeParser
) : IBBRepository {
    /**
     * getData is used to get data from the database and from the remote server.
     * If there is internet connection, it gets data from the remote server. If it fails, it gets data from the local database.
     * @return a flow of ConnectionStatus
     */
    override suspend fun getData(): Flow<ConnectionStatus<BBInfo>> = flow {
        val bbInfoList = dao.getAll().map { it.toDomain(bbTypeParser) }
        emit(Loading(bbInfoList))
        val remoteData = api.getProductInfo()
        if(remoteData != null) {
            val newBBInfoList = remoteData.result.list.map {
                dao.upsert(it.toEntity(bbTypeParser))
                it.toDomain()
            }
            emit(Success(newBBInfoList))
        } else emit(Error(bbInfoList, NO_DATA))
    }.catch { ex ->
        val bbInfoList = dao.getAll().map { it.toDomain(bbTypeParser) }
        when(ex) {
            is IOException -> emit(Error(bbInfoList, NO_INTERNET))
            is HttpException -> emit(Error(bbInfoList, CONNECTION_ERROR))
            is JsonParseException -> emit(Error(bbInfoList, SERIALIZATION_ERROR))
            else -> emit(Error(bbInfoList, UNKNOWN))
        }
    }
    /**
     * updateRecord is used to update a record in the database
     * @param record the record to update
     */
    override suspend fun updateRecord(record: BBInfo) = dao.upsert(record.toEntity(bbTypeParser))
}