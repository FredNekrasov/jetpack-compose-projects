package com.fredprojects.helloworld.di

import com.fredprojects.core.database.dao.IJDDao
import com.fredprojects.core.database.dao.IPWDao
import com.fredprojects.core.database.di.dbModule
import com.fredprojects.features.clients.data.di.clientsDataModule
import com.fredprojects.features.jumps.data.repositories.JDRepository
import com.fredprojects.features.jumps.domain.repositories.IJDRepository
import com.fredprojects.features.pws.data.repositories.PWRepository
import com.fredprojects.features.pws.domain.repository.IPWRepository
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val dataModule = module {
    includes(dbModule, clientsDataModule)
    single<IPWRepository>(qualifier<IPWRepository>()) {
        PWRepository(get(qualifier<IPWDao>()))
    }
    single<IJDRepository>(qualifier<IJDRepository>()) {
        JDRepository(get(qualifier<IJDDao>()))
    }
}