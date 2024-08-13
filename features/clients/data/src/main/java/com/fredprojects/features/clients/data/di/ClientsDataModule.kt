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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ClientsDataModule {
    @Singleton
    @Provides
    fun provideMathService(): IMathService = createMathService()
    @Singleton
    @Provides
    fun provideAstronomyInfoService(): IAstronomyInfoService = createAstronomyInfoService()
    @Singleton
    @Provides
    fun provideBBService(): IBBService = createBBService()
    @Singleton
    @Provides
    fun provideMathRepository(
        dao: IMathDao, api: IMathService
    ): IMathRepository = MathRepository(dao, api)
    @Singleton
    @Provides
    fun provideAstronomyInfoRepository(
        dao: IAstronomyInfoDao, api: IAstronomyInfoService
    ): IAstronomyRepository = AstronomyInfoRepository(dao, api)
    @Singleton
    @Provides
    fun provideBBRepository(
        dao: IBBDao, api: IBBService
    ): IBBRepository = BBRepository(dao, api, BBTypeParser(Gson()))
}