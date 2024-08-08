package com.fredprojects.features.pws.data.repositories

import com.fredprojects.core.database.dao.IPWDao
import com.fredprojects.features.pws.data.mappers.*
import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.domain.repository.IPWRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Practical work repository implementation based on local database
 * @param dao - database access object for practical work. See [IPWDao]
 */
class PWRepository(
    private val dao: IPWDao
) : IPWRepository {
    /**
     * Upsert a practical work into database.
     * @param pw [PracticalWork]
     * @see [IPWDao.upsert]
     */
    override suspend fun upsert(pw: PracticalWork) = dao.upsert(pw.toEntity())
    /**
     * Delete a practical work from database.
     * @param pw [PracticalWork]
     * @see [IPWDao.delete]
     */
    override suspend fun delete(pw: PracticalWork) = dao.delete(pw.toEntity())
    /**
     * Get all practical works from database.
     * @see [IPWDao.getAll]
     */
    override fun getData(): Flow<List<PracticalWork>> = dao.getAll().map { pwList -> pwList.map { it.toDomain() } }
    /**
     * Get a practical work by id from database.
     * @param id - practical work id
     * @see [IPWDao.getById]
     */
    override suspend fun getRecordById(id: Int): PracticalWork? = dao.getById(id)?.toDomain()
}