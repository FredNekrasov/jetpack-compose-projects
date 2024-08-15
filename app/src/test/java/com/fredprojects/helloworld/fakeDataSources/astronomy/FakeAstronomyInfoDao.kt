package com.fredprojects.helloworld.fakeDataSources.astronomy

import com.fredprojects.core.database.dao.IAstronomyInfoDao
import com.fredprojects.core.database.entities.AstronomyInfoEntity

class FakeAstronomyInfoDao : IAstronomyInfoDao {
    private val astronomyInfoList = mutableListOf<AstronomyInfoEntity>()
    override suspend fun insert(listInfo: List<AstronomyInfoEntity>) {
        astronomyInfoList.addAll(listInfo)
    }
    override suspend fun getAll(
        ra: String, dec: String, radius: Float
    ): List<AstronomyInfoEntity> = astronomyInfoList.toList()
    override suspend fun delete(listInfo: List<String>) {
        astronomyInfoList.removeIf { it.name in listInfo }
    }
}