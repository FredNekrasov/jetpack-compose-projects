package com.fredprojects.helloworld.fakeDataSources.jumps

import com.fredprojects.core.database.dao.IJDDao
import com.fredprojects.core.database.entities.JDEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeJDDao: IJDDao {
    private val jdList = mutableListOf<JDEntity>()
    override suspend fun upsert(jdEntity: JDEntity) {
        jdEntity.id?.let { jdList[it] = jdEntity } ?: jdList.add(jdEntity.copy(id = jdList.size))
    }
    override suspend fun delete(jdEntity: JDEntity) {
        jdList.remove(jdEntity)
    }
    override fun getAll(): Flow<List<JDEntity>> = flow { emit(jdList) }
}