package com.fredprojects.helloworld.di

import com.fredprojects.features.auth.domain.useCases.UserUseCases
import com.fredprojects.features.auth.presentation.vm.UserVM
import com.fredprojects.features.clients.domain.astronomy.repository.IAstronomyRepository
import com.fredprojects.features.clients.domain.bybit.repository.IBBRepository
import com.fredprojects.features.clients.domain.math.repository.IMathRepository
import com.fredprojects.features.clients.presentation.astronomy.AstronomyInfoVM
import com.fredprojects.features.clients.presentation.bybit.ByBitVM
import com.fredprojects.features.clients.presentation.math.MathVM
import com.fredprojects.features.inequality.api.useCases.InequalityUseCase
import com.fredprojects.features.inequality.impl.InequalityVM
import com.fredprojects.features.jumps.domain.useCases.JDUseCases
import com.fredprojects.features.jumps.presentation.vm.*
import com.fredprojects.features.pws.domain.useCases.PWUseCases
import com.fredprojects.features.pws.presentation.vm.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.withOptions
import org.koin.core.qualifier._q
import org.koin.dsl.module

val presentationModule = module {
    viewModel { 
        PWListVM(get(_q<PWUseCases>()))
    }.withOptions { qualifier = _q<PWListVM>() }

    viewModel { 
        UpsertPWVM(get(_q<PWUseCases>()), get())
    }.withOptions { qualifier = _q<UpsertPWVM>() }

    viewModel {
        JDListVM(get(_q<JDUseCases>()))
    }.withOptions { qualifier = _q<JDListVM>() }

    viewModel {
        JumpingRopeVM(get(_q<JDUseCases>()))
    }.withOptions { qualifier = _q<JumpingRopeVM>() }

    viewModel {
        InequalityVM(get(_q<InequalityUseCase>()))
    }.withOptions { qualifier = _q<InequalityVM>() }

    viewModel {
        AstronomyInfoVM(get(_q<IAstronomyRepository>()))
    }.withOptions { qualifier = _q<AstronomyInfoVM>() }

    viewModel {
        MathVM(get(_q<IMathRepository>()))
    }.withOptions { qualifier = _q<MathVM>() }

    viewModel {
        ByBitVM(get(_q<IBBRepository>()))
    }.withOptions { qualifier = _q<ByBitVM>() }

    viewModel {
        UserVM(get(_q<UserUseCases>()))
    }.withOptions { qualifier = _q<UserVM>() }
}