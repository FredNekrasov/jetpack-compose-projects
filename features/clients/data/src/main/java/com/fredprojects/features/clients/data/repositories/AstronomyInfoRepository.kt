package com.fredprojects.features.clients.data.repositories

import com.fredprojects.core.database.dao.IAstronomyInfoDao
import com.fredprojects.features.clients.data.mappers.*
import com.fredprojects.features.clients.data.remote.services.IAstronomyInfoService
import com.fredprojects.features.clients.domain.astronomy.models.AstronomyInfo
import com.fredprojects.features.clients.domain.astronomy.repository.IAstronomyRepository
import com.fredprojects.features.clients.domain.utils.ActionStatus.*
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
import com.fredprojects.features.clients.domain.utils.ConnectionStatus.*
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException

/**
 * The AstronomyInfo repository uses local and remote data sources to extract and store data.
 * The data is stored in a local database and retrieved from there when there is no internet connection.
 * @param dao the local data source. See [IAstronomyInfoDao]
 * @param api the remote data source that provides the data. See [IAstronomyInfoService]
 */
class AstronomyInfoRepository(
    private val dao: IAstronomyInfoDao,
    private val api: IAstronomyInfoService
) : IAstronomyRepository {
    /**
     * Fetches data from the remote source and stores it in the local database.
     * @param ra the right ascension
     * @param dec the declination
     * @param radius the radius in degrees
     * @return Flow<ConnectionStatus<List<AstronomyInfo>>>
     * @see ConnectionStatus
     */
    override fun getData(
        ra: String, dec: String, radius: Float
    ): Flow<ConnectionStatus<AstronomyInfo>> = flow {
        val astronomyInfoList = dao.getAll(ra, dec, radius).map { it.toDomain() }
        emit(Loading(astronomyInfoList))
        val remoteData = api.getAstronomyInfo(ra, dec, radius)
        if(remoteData != null) {
            dao.delete(remoteData.map { it.key })
            dao.insert(remoteData.map { it.value.toEntity(ra, it.key, dec, radius) })
            emit(Success(dao.getAll(ra, dec, radius).map { it.toDomain() }))
        } else emit(Error(astronomyInfoList, NO_DATA))
    }.catch { ex ->
        val astronomyInfoList = dao.getAll(ra, dec, radius).map { it.toDomain() }
        when(ex) {
            is JsonSyntaxException -> emit(Error(astronomyInfoList, SERIALIZATION_ERROR))
            is HttpException -> emit(Error(astronomyInfoList, CONNECTION_ERROR))
            is IOException -> emit(Error(astronomyInfoList, NO_INTERNET))
            else -> emit(Error(astronomyInfoList, UNKNOWN))
        }
    }
}