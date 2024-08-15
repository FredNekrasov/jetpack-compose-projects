package com.fredprojects.helloworld.fakeDataSources.bybit

import com.fredprojects.features.clients.data.remote.dto.*
import com.fredprojects.features.clients.data.remote.services.IBBService
import com.fredprojects.features.clients.domain.bybit.models.BBType
import kotlin.random.Random

class FakeBBService : IBBService {
    override suspend fun getProductInfo(): BBResponseDto? {
        val i = Random(10).nextInt()
        return if((i % 2) == 0) null else BBResponseDto(BBResultDto(getList(), 9), 200, "ok", 0
        )
    }
    private fun getList() = listOf(
        BBInfoDto(
            0L, "Bybit1", 0L, 0L, listOf("Bybit", "USDT", "SPOT"),
            "helloWorld1", BBType("SPOT", "SPOT"), "SPOT"
        ),
        BBInfoDto(
            0L, "Bybit2", 0L, 0L, listOf("Bybit", "USDT", "SPOT"),
            "helloWorld2", BBType("SPOT", "SPOT"), "SPOT"
        )
    )
}