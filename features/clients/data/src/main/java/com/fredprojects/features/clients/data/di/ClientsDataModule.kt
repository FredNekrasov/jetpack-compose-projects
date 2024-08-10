package com.fredprojects.features.clients.data.di

import com.fredprojects.core.database.dao.*
import com.fredprojects.features.clients.data.mappers.BBTypeParser
import com.fredprojects.features.clients.data.remote.*
import com.fredprojects.features.clients.data.remote.services.*
import com.fredprojects.features.clients.data.repositories.*
import com.fredprojects.features.clients.domain.astronomy.repository.IAstronomyRepository
import com.fredprojects.features.clients.domain.bybit.repository.IBBRepository
import com.fredprojects.features.clients.domain.math.repository.IMathRepository
import com.google.gson.Gson
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val clientsDataModule = module {
    single(qualifier<IMathService>(), createdAtStart = true) {
        createMathService()
    }
    single(qualifier<IAstronomyInfoService>(), createdAtStart = true) {
        createAstronomyInfoService()
    }
    single(qualifier<IBBService>(), createdAtStart = true) {
        createBBService()
    }
    single<IMathRepository>(qualifier<IMathRepository>()) {
        MathRepository(
            dao = get(qualifier<IMathDao>()),
            api = get(qualifier<IMathService>())
        )
    }
    single<IAstronomyRepository>(qualifier<IAstronomyRepository>()) {
        AstronomyInfoRepository(
            dao = get(qualifier<IAstronomyInfoDao>()),
            api = get(qualifier<IAstronomyInfoService>())
        )
    }
    single(qualifier<BBTypeParser>(), createdAtStart = true) {
        BBTypeParser(Gson())
    }
    single<IBBRepository>(qualifier<IBBRepository>()) {
        BBRepository(
            dao = get(qualifier<IBBDao>()),
            api = get(qualifier<IBBService>()),
            bbTypeParser = get(qualifier<BBTypeParser>())
        )
    }
}