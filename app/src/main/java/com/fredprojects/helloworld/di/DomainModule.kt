package com.fredprojects.helloworld.di

import com.fredprojects.features.auth.domain.repository.IUserRepository
import com.fredprojects.features.auth.domain.useCases.*
import com.fredprojects.features.fibonacci.api.useCases.FibonacciUseCase
import com.fredprojects.features.inequality.api.useCases.InequalityUseCase
import com.fredprojects.features.jumps.domain.repositories.IJDRepository
import com.fredprojects.features.jumps.domain.useCases.JDUseCases
import com.fredprojects.features.jumps.domain.useCases.crud.*
import com.fredprojects.features.pws.domain.repository.IPWRepository
import com.fredprojects.features.pws.domain.useCases.PWUseCases
import com.fredprojects.features.pws.domain.useCases.crud.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DomainModule {
    @Provides
    fun provideInequalityUseCase(): InequalityUseCase = InequalityUseCase()
    @Provides
    fun provideFibonacciUseCase(): FibonacciUseCase = FibonacciUseCase()
    @Provides
    fun providePWUseCase(repository: IPWRepository): PWUseCases = PWUseCases(
        getPWs = GetPWUseCase(repository),
        upsert = UpsertPWUseCase(repository),
        delete = DeletePWUseCase(repository)
    )
    @Provides
    fun provideJDUseCase(repository: IJDRepository): JDUseCases = JDUseCases(
        getData = GetJDUseCase(repository),
        upsert = UpsertJDUseCase(repository),
        delete = DeleteJDUseCase(repository)
    )
    @Provides
    fun provideUserUseCase(repository: IUserRepository): UserUseCases = UserUseCases(
        auth = AuthUseCase(repository),
        upsert = UpsertUserUseCase(repository),
        delete = DeleteUserUseCase(repository)
    )
}