package com.fredprojects.helloworld.di

import com.fredprojects.core.database.dao.*
import com.fredprojects.features.auth.data.repository.UserRepository
import com.fredprojects.features.auth.domain.repository.IUserRepository
import com.fredprojects.features.jumps.data.repositories.JDRepository
import com.fredprojects.features.jumps.domain.repositories.IJDRepository
import com.fredprojects.features.pws.data.repositories.PWRepository
import com.fredprojects.features.pws.domain.repository.IPWRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {
    @Singleton
    @Provides
    fun providePWRepository(dao: IPWDao): IPWRepository = PWRepository(dao)
    @Singleton
    @Provides
    fun provideJDRepository(dao: IJDDao): IJDRepository = JDRepository(dao)
    @Singleton
    @Provides
    fun provideUserRepository(dao: IUserDao): IUserRepository = UserRepository(dao)
}