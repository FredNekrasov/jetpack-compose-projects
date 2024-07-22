package com.fredprojects.helloworld.data.repositories

import com.fredprojects.helloworld.data.local.dao.IJDDao
import com.fredprojects.helloworld.data.mappers.toDomain
import com.fredprojects.helloworld.data.mappers.toEntity
import com.fredprojects.helloworld.domain.core.repositories.IRepository
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * JumpData repository is used to store and retrieve data from the local database.
 * @see IJDDao
 */
class JDRepository(
    private val dao: IJDDao
) : IRepository<JumpData> {
    /**
     * Updates or inserts a record into the database.
     * @param item [JumpData]
     * @see [IJDDao.upsert]
     */
    override suspend fun upsert(item: JumpData) = dao.upsert(item.toEntity())
    /**
     * Deletes a record from the database.
     * @param item [JumpData]
     * @see [IJDDao.delete]
     */
    override suspend fun delete(item: JumpData) = dao.delete(item.toEntity())
    /**
     * Gets all records from the database.
     * @see [IJDDao.getAll]
     */
    override fun getData(): Flow<List<JumpData>> = dao.getAll().map { pwList -> pwList.map { it.toDomain() } }
    /**
     * Gets a record from the database by id.
     * @param id [Int]
     * @see [IJDDao.getById]
     */
    override suspend fun getRecordById(id: Int): JumpData? = dao.getById(id)?.toDomain()
}