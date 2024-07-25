package com.fredprojects.helloworld.data.di

import com.fredprojects.helloworld.data.local.HelloWorldDb
import com.fredprojects.helloworld.data.repositories.*
import com.fredprojects.helloworld.domain.core.repositories.IClientRepository
import com.fredprojects.helloworld.domain.core.repositories.IRepository
import com.fredprojects.helloworld.domain.features.clients.astronomy.repository.IAstronomyRepository
import com.fredprojects.helloworld.domain.features.clients.common.AnimeQuote
import com.fredprojects.helloworld.domain.features.clients.common.MathModel
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import org.koin.core.module.dsl.withOptions
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

internal val repositoryModule = module {
    single<IRepository<JumpData>> {
        JDRepository(get<HelloWorldDb>(named(DMQualifiers.HELLO_WORLD_DB)).jdDao)
    }.withOptions { qualifier(DMQualifiers.JD_REPOSITORY) }

    single<IRepository<PracticalWork>> {
        PWRepository(get<HelloWorldDb>(named(DMQualifiers.HELLO_WORLD_DB)).pwDao)
    }.withOptions { qualifier(DMQualifiers.PW_REPOSITORY) }

    single<IClientRepository<AnimeQuote>> {
        AQRepository(
            get<HelloWorldDb>(named(DMQualifiers.HELLO_WORLD_DB)).aqDao,
            get(named(DMQualifiers.AQ_SERVICE))
        )
    }.withOptions { qualifier(DMQualifiers.AQ_REPOSITORY) }

    single<IClientRepository<MathModel>> {
        MathRepository(
            get<HelloWorldDb>(named(DMQualifiers.HELLO_WORLD_DB)).mathDao,
            get(named(DMQualifiers.MATH_SERVICE))
        )
    }.withOptions { qualifier(DMQualifiers.MATH_REPOSITORY) }

    single<IAstronomyRepository> {
        AstronomyInfoRepository(
            get<HelloWorldDb>(named(DMQualifiers.HELLO_WORLD_DB)).astronomyInfoDao,
            get(named(DMQualifiers.ASTRONOMY_INFO_SERVICE))
        )
    }.withOptions { qualifier(DMQualifiers.ASTRONOMY_INFO_REPOSITORY) }
}