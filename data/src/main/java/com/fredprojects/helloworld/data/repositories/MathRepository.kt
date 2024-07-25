package com.fredprojects.helloworld.data.repositories

import com.fredprojects.helloworld.data.local.dao.IMathDao
import com.fredprojects.helloworld.data.mappers.toDomain
import com.fredprojects.helloworld.data.mappers.toEntity
import com.fredprojects.helloworld.data.remote.services.IMathService
import com.fredprojects.helloworld.domain.core.repositories.IClientRepository
import com.fredprojects.helloworld.domain.core.utils.ActionStatus.*
import com.fredprojects.helloworld.domain.core.utils.ConnectionStatus
import com.fredprojects.helloworld.domain.features.clients.common.MathModel
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.flow.*
import retrofit2.HttpException

/**
 * MathRepository repository uses local and remote data sources to extract and store data.
 * The data is stored in a local database and retrieved from there when there is no internet connection.
 * @param dao the local data source. See [IMathDao]
 * @param api the remote data source that provides the data. See [IMathService]
 */
class MathRepository(
    private val dao: IMathDao,
    private val api: IMathService
) : IClientRepository<MathModel> {
    /**
     * Fetches data from the remote source and stores it in the local database.
     * @param data the expression
     * @return Flow<ConnectionStatus<List<MathModel>>>
     * @see ConnectionStatus
     */
    override fun getData(data: String): Flow<ConnectionStatus<MathModel>> = flow {
        val solutions = dao.getByExpression(data)?.let { listOf(it.toDomain()) } ?: dao.getAll().map { it.toDomain() }
        emit(ConnectionStatus.Loading(solutions))
        val response = api.getResult(data).execute()
        if (response.isSuccessful) {
            val body = response.body() ?: return@flow emit(ConnectionStatus.Error(solutions, NO_DATA))
            dao.deleteByExpression(data)
            dao.insert(body.toEntity())
            emit(ConnectionStatus.Success(listOf(body)))
        } else emit(ConnectionStatus.Error(solutions, CONNECTION_ERROR))
    }.catch { ex ->
        val solutions = dao.getAll().map { it.toDomain() }
        when(ex) {
            is JsonSyntaxException -> emit(ConnectionStatus.Error(solutions, SERIALIZATION_ERROR))
            is HttpException -> emit(ConnectionStatus.Error(solutions, NO_INTERNET))
            else -> emit(ConnectionStatus.Error(solutions, UNKNOWN))
        }
    }
}