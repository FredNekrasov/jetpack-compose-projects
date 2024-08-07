package com.fredprojects.features.clients.data.repositories

import com.fredprojects.core.database.dao.IMathDao
import com.fredprojects.features.clients.data.mappers.*
import com.fredprojects.features.clients.data.remote.services.IMathService
import com.fredprojects.features.clients.domain.math.models.MathModel
import com.fredprojects.features.clients.domain.math.repository.IMathRepository
import com.fredprojects.features.clients.domain.utils.ActionStatus.*
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
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
) : IMathRepository {
    /**
     * Fetches data from the remote source and stores it in the local database.
     * @param expression the expression
     * @return Flow<ConnectionStatus<List<MathModel>>>
     * @see ConnectionStatus
     */
    override fun getData(expression: String): Flow<ConnectionStatus<MathModel>> = flow {
        val solutions = dao.getByExpression(expression)?.let { listOf(it.toDomain()) } ?: dao.getAll().map { it.toDomain() }
        emit(ConnectionStatus.Loading(solutions))
        val response = api.getResult(expression).execute()
        if (response.isSuccessful) {
            val body = response.body() ?: return@flow emit(ConnectionStatus.Error(solutions, NO_DATA))
            dao.deleteByExpression(expression)
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