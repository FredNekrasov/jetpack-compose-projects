package com.fredprojects.helloworld.di

import com.fredprojects.features.auth.data.repository.UserRepository
import com.fredprojects.features.auth.domain.useCases.*
import com.fredprojects.features.jump.data.repository.JDRepository
import com.fredprojects.features.jump.domain.usecases.*
import com.fredprojects.features.jump.domain.usecases.crud.*
import com.fredprojects.features.math.domain.usecases.*
import com.fredprojects.features.pws.data.repository.PWRepository
import com.fredprojects.features.pws.domain.useCases.PWUseCases
import com.fredprojects.features.pws.domain.useCases.crud.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DomainModule {
    @Provides
    fun provideInequalityUseCase(): InequalityUseCase = InequalityUseCase()

    @Provides
    fun provideFibonacciUseCase(): FibonacciUseCase = FibonacciUseCase()

    @Provides
    fun providePWUseCase(repository: PWRepository): PWUseCases = PWUseCases(
        getPWs = GetPWUseCase(repository),
        upsert = UpsertPWUseCase(repository),
        delete = DeletePWUseCase(repository)
    )

    @Singleton
    @Provides
    fun provideCalculateJumpUseCase(): CalculateJumpUseCase = CalculateJumpUseCase()

    @Provides
    fun provideUpsertJDUseCase(repository: JDRepository): UpsertJDUseCase = UpsertJDUseCase(repository)

    @Provides
    fun provideJDUseCase(repository: JDRepository, upsertJDUseCase: UpsertJDUseCase): JDUseCases = JDUseCases(
        getData = GetJDUseCase(repository),
        upsert = upsertJDUseCase,
        delete = DeleteJDUseCase(repository)
    )

    @Provides
    fun provideUserUseCase(repository: UserRepository): UserUseCases = UserUseCases(
        auth = AuthUseCase(repository),
        upsert = UpsertUserUseCase(repository),
        delete = DeleteUserUseCase(repository)
    )
}