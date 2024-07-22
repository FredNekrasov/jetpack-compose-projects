package com.fredprojects.helloworld.data.repositories

import com.fredprojects.helloworld.data.local.dao.IPWDao
import com.fredprojects.helloworld.data.mappers.toDomain
import com.fredprojects.helloworld.data.mappers.toEntity
import com.fredprojects.helloworld.domain.core.repositories.IRepository
import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Practical work repository implementation based on local database
 * @param dao - database access object for practical work. See [IPWDao]
 */
class PWRepository(
    private val dao: IPWDao
) : IRepository<PracticalWork> {
    /**
     * Upsert a practical work into database.
     * @param item [PracticalWork]
     * @see [IPWDao.upsert]
     */
    override suspend fun upsert(item: PracticalWork) = dao.upsert(item.toEntity())
    /**
     * Delete a practical work from database.
     * @param item [PracticalWork]
     * @see [IPWDao.delete]
     */
    override suspend fun delete(item: PracticalWork) = dao.delete(item.toEntity())
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