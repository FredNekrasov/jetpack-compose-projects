package com.fredprojects.helloworld.di

import com.fredprojects.helloworld.data.di.DMQualifiers
import com.fredprojects.helloworld.presentation.features.clients.anime.AnimeQuotesVM
import com.fredprojects.helloworld.presentation.features.clients.astronomy.AstronomyInfoVM
import com.fredprojects.helloworld.presentation.features.clients.math.MathVM
import com.fredprojects.helloworld.presentation.features.inequality.InequalityVM
import com.fredprojects.helloworld.presentation.features.jumps.vm.*
import com.fredprojects.helloworld.presentation.features.pws.vm.PWListVM
import com.fredprojects.helloworld.presentation.features.pws.vm.UpsertPWVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.withOptions
import org.koin.core.qualifier.*
import org.koin.dsl.module

val presentationModule = module {
    viewModel { 
        PWListVM(get(named(AppQualifiers.PW_USE_CASE)))
    }.withOptions { qualifier = qualifier(AppQualifiers.PW_LIST_VM) }

    viewModel { 
        UpsertPWVM(get(named(AppQualifiers.PW_USE_CASE)), get())
    }.withOptions { qualifier = qualifier(AppQualifiers.UPSERT_PW_VM) }

    viewModel {
        JDListVM(get(named(AppQualifiers.JD_USE_CASE)))
    }.withOptions { qualifier = qualifier(AppQualifiers.JD_LIST_VM) }

    viewModel {
        JumpingRopeVM(get(named(AppQualifiers.JD_USE_CASE)))
    }.withOptions { qualifier = qualifier(AppQualifiers.JUMPING_ROPE_VM) }

    viewModel {
        InequalityVM(get(named(AppQualifiers.INEQUALITY_USE_CASE)))
    }.withOptions { qualifier = qualifier(AppQualifiers.INEQUALITY_VM) }

    viewModel {
        AnimeQuotesVM(get(_q(DMQualifiers.AQ_REPOSITORY)))
    }.withOptions { qualifier = qualifier(AppQualifiers.ANIME_QUOTES_VM) }

    viewModel {
        AstronomyInfoVM(get(_q(DMQualifiers.ASTRONOMY_INFO_REPOSITORY)))
    }.withOptions { qualifier = qualifier(AppQualifiers.ASTRONOMY_INFO_VM) }

    viewModel {
        MathVM(get(_q(DMQualifiers.MATH_REPOSITORY)))
    }.withOptions { qualifier = qualifier(AppQualifiers.MATH_VM) }
}