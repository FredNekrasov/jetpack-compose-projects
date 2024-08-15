package com.fredprojects.helloworld.fakeDataSources.bybit

import com.fredprojects.core.database.dao.IBBDao
import com.fredprojects.core.database.entities.BBInfoEntity

class FakeBBDao : IBBDao {
    private val bbInfoList = mutableListOf<BBInfoEntity>()
    override suspend fun upsert(bbInfoEntity: BBInfoEntity) {
        if(bbInfoEntity.id == null) bbInfoList.add(bbInfoEntity) else bbInfoList[bbInfoEntity.id!!] = bbInfoEntity
    }
    override suspend fun getAll(): List<BBInfoEntity> = bbInfoList
    override suspend fun delete(title: String) {
        bbInfoList.removeIf { it.title == title }
    }
}