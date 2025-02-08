package com.fredprojects.features.jump.data.repository

import com.fredprojects.core.database.dao.IJDDao
import com.fredprojects.features.jump.data.mapper.*
import com.fredprojects.features.jump.domain.models.JumpData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JDRepository @Inject constructor(
    private val dao: IJDDao
) {
    /**
     * upsert data in the database
     * if the data exists in the database, it will be updated
     * if the data does not exist in the database, it will be inserted
     * @param jd the data to be inserted or updated
     */
    suspend fun upsert(jd: JumpData) = dao.upsert(jd.toEntity())
    /**
     * Deletes a record from the database.
     * @param jd [JumpData]
     * @see [IJDDao.delete]
     */
    suspend fun delete(jd: JumpData) = dao.delete(jd.toEntity())
    /**
     * Gets all records from the database.
     * @see [IJDDao.getAll]
     */
    fun getData(): Flow<List<JumpData>> = dao.getAll().map { pwList -> pwList.map { it.toDomain() } }
}