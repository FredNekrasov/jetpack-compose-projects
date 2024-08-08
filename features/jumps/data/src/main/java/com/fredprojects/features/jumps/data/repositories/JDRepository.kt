package com.fredprojects.features.jumps.data.repositories

import com.fredprojects.core.database.dao.IJDDao
import com.fredprojects.features.jumps.data.mappers.*
import com.fredprojects.features.jumps.domain.models.JumpData
import com.fredprojects.features.jumps.domain.repositories.IJDRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * JumpData repository is used to store and retrieve data from the local database.
 * @see IJDDao
 */
class JDRepository(
    private val dao: IJDDao
) : IJDRepository {
    /**
     * Updates or inserts a record into the database.
     * @param jd [JumpData]
     * @see [IJDDao.upsert]
     */
    override suspend fun upsert(jd: JumpData) = dao.upsert(jd.toEntity())
    /**
     * Deletes a record from the database.
     * @param jd [JumpData]
     * @see [IJDDao.delete]
     */
    override suspend fun delete(jd: JumpData) = dao.delete(jd.toEntity())
    /**
     * Gets all records from the database.
     * @see [IJDDao.getAll]
     */
    override fun getData(): Flow<List<JumpData>> = dao.getAll().map { pwList -> pwList.map { it.toDomain() } }
}