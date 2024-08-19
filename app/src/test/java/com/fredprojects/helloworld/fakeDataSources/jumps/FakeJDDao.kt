package com.fredprojects.helloworld.fakeDataSources.jumps

import com.fredprojects.core.database.dao.IJDDao
import com.fredprojects.core.database.entities.JDEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeJDDao: IJDDao {
    private val jdList = mutableListOf<JDEntity>()
    override suspend fun upsert(jdEntity: JDEntity) {
        if(jdEntity.id == null) jdList.add(jdEntity.copy(id = jdList.size)) else jdList[jdEntity.id!!] = jdEntity
    }
    override suspend fun delete(jdEntity: JDEntity) {
        jdList.remove(jdEntity)
    }
    override fun getAll(): Flow<List<JDEntity>> = flow { emit(jdList) }
}