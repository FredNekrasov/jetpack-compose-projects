package com.fredprojects.helloworld.fakeDataSources.astronomy

import com.fredprojects.features.clients.data.remote.dto.AstronomyInfoDto
import com.fredprojects.features.clients.data.remote.services.IAstronomyInfoService

class FakeAstronomyInfoService : IAstronomyInfoService {
    override suspend fun getAstronomyInfo(
        ra: String, dec: String, radius: Float
    ): Map<String, AstronomyInfoDto>? {
        if(ra.isBlank() || dec.isBlank() || radius == 2f) return null
        return mapOf(
            "test1" to AstronomyInfoDto(references = listOf("test"), name = listOf("test1")),
            "test2" to AstronomyInfoDto(references = listOf("test"), name = listOf("test2")),
            "test3" to AstronomyInfoDto(references = listOf("test"), name = listOf("test3")),
            "test4" to AstronomyInfoDto(references = listOf("test"), name = listOf("test4")),
            "test5" to AstronomyInfoDto(references = listOf("test"), name = listOf("test5")),
            "test6" to AstronomyInfoDto(references = listOf("test"), name = listOf("test6")),
            "test7" to AstronomyInfoDto(references = listOf("test"), name = listOf("test7")),
            "test8" to AstronomyInfoDto(references = listOf("test"), name = listOf("test8")),
            "test9" to AstronomyInfoDto(references = listOf("test"), name = listOf("test9"))
        )
    }
}