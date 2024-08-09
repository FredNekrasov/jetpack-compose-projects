package com.fredprojects.helloworld.di

import com.fredprojects.features.fibonacci.api.useCases.FibonacciUseCase
import com.fredprojects.features.inequality.api.useCases.InequalityUseCase
import com.fredprojects.features.jumps.domain.repositories.IJDRepository
import com.fredprojects.features.jumps.domain.useCases.JDUseCases
import com.fredprojects.features.jumps.domain.useCases.crud.*
import com.fredprojects.features.pws.domain.repository.IPWRepository
import com.fredprojects.features.pws.domain.useCases.PWUseCases
import com.fredprojects.features.pws.domain.useCases.crud.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    factory(named<InequalityUseCase>()) { InequalityUseCase() }
    factory(named<FibonacciUseCase>()) { FibonacciUseCase() }
    factory(named<PWUseCases>()) {
        PWUseCases(
            getPWs = GetPWUseCase(get(named<IPWRepository>())),
            upsert = UpsertPWUseCase(get(named<IPWRepository>())),
            delete = DeletePWUseCase(get(named<IPWRepository>()))
        )
    }
    factory(named<JDUseCases>()) {
        JDUseCases(
            getData = GetJDUseCase(get(named<IJDRepository>())),
            upsert = UpsertJDUseCase(get(named<IJDRepository>())),
            delete = DeleteJDUseCase(get(named<IJDRepository>()))
        )
    }
} 