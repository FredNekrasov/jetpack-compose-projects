package com.fredprojects.helloworld.fakeDataSources.pws

import com.fredprojects.core.database.dao.IPWDao
import com.fredprojects.core.database.entities.PWEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePWDao : IPWDao {
    private val pwList = mutableListOf<PWEntity>()
    override suspend fun upsert(pwEntity: PWEntity) {
        if(pwEntity.id == null) pwList.add(pwEntity.copy(id = pwList.size)) else pwList[pwEntity.id!!] = pwEntity
    }
    override suspend fun delete(pwEntity: PWEntity) {
        pwList.remove(pwEntity)
    }
    override fun getAll(): Flow<List<PWEntity>> = flow { emit(pwList) }
    override suspend fun getById(id: Int): PWEntity? = pwList.firstOrNull { it.id == id }
}