package com.fredprojects.features.clients.data.di

import com.fredprojects.core.database.dao.IAstronomyInfoDao
import com.fredprojects.core.database.dao.IMathDao
import com.fredprojects.features.clients.data.remote.*
import com.fredprojects.features.clients.data.remote.services.*
import com.fredprojects.features.clients.data.repositories.*
import com.fredprojects.features.clients.domain.astronomy.repository.IAstronomyRepository
import com.fredprojects.features.clients.domain.math.repository.IMathRepository
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val clientsDataModule = module {
    single(qualifier<IMathService>(), createdAtStart = true) {
        createMathService()
    }
    single(qualifier<IAstronomyInfoService>(), createdAtStart = true) {
        createAstronomyInfoService()
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
}