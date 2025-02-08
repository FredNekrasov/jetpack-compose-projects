package com.fredprojects.features.pws.data.repository

import androidx.compose.ui.util.fastMap
import com.fredprojects.core.database.dao.IPWDao
import com.fredprojects.features.pws.data.mapper.toDomain
import com.fredprojects.features.pws.data.mapper.toEntity
import com.fredprojects.features.pws.domain.models.PracticalWork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PWRepository @Inject constructor(
    private val dao: IPWDao
) {
    /**
     * upsert data in the database
     * if the data exists in the database, it will be updated
     * if the data does not exist in the database, it will be inserted
     * @param pw the data to be inserted or updated
     */
    suspend fun upsert(pw: PracticalWork) = dao.upsert(pw.toEntity())
    /**
     * Delete a practical work from database.
     * @param pw [PracticalWork] to be deleted
     * @see [IPWDao.delete]
     */
    suspend fun delete(pw: PracticalWork) = dao.delete(pw.toEntity())
    /**
     * Get all practical works from database.
     * @see [IPWDao.getAll]
     */
    fun getData(): Flow<List<PracticalWork>> = dao.getAll().map { pwList -> pwList.fastMap { it.toDomain() } }
    /**
     * Get a practical work by id from database.
     * @param id - practical work id
     * @see [IPWDao.getById]
     */
    suspend fun getRecordById(id: Int): PracticalWork? = dao.getById(id)?.toDomain()
}