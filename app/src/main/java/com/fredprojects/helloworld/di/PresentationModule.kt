package com.fredprojects.helloworld.di

import com.fredprojects.helloworld.data.di.DMQualifiers
import com.fredprojects.helloworld.presentation.features.clients.anime.vm.AnimeQuotesVM
import com.fredprojects.helloworld.presentation.features.clients.astronomy.vm.AstronomyInfoVM
import com.fredprojects.helloworld.presentation.features.clients.math.vm.MathVM
import com.fredprojects.helloworld.presentation.features.inequality.vm.InequalityVM
import com.fredprojects.helloworld.presentation.features.jumps.list.vm.JDListVM
import com.fredprojects.helloworld.presentation.features.jumps.rope.vm.JumpingRopeVM
import com.fredprojects.helloworld.presentation.features.pws.vm.PWListVM
import com.fredprojects.helloworld.presentation.features.pws.vm.UpsertPWVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.withOptions
import org.koin.core.qualifier.*
import org.koin.dsl.module

val presentationModule = module {
    viewModel { 
        PWListVM(get(named(AppQualifiers.PW_USE_CASE)))
    }.withOptions { qualifier(AppQualifiers.PW_LIST_VM) }

    viewModel { 
        UpsertPWVM(get(named(AppQualifiers.PW_USE_CASE)), get())
    }.withOptions { qualifier(AppQualifiers.UPSERT_PW_VM) }

    viewModel {
        JDListVM(get(named(AppQualifiers.JD_USE_CASE)))
    }.withOptions { qualifier(AppQualifiers.JD_LIST_VM) }

    viewModel {
        JumpingRopeVM(get(named(AppQualifiers.JD_USE_CASE)))
    }.withOptions { qualifier(AppQualifiers.JUMPING_ROPE_VM) }

    viewModel {
        InequalityVM(get(named(AppQualifiers.INEQUALITY_USE_CASE)))
    }.withOptions { qualifier(AppQualifiers.INEQUALITY_VM) }

    viewModel {
        MathVM(get(_q(DMQualifiers.MATH_REPOSITORY)))
    }.withOptions { qualifier(AppQualifiers.MATH_VM) }

    viewModel {
        AnimeQuotesVM(get(_q(DMQualifiers.AQ_REPOSITORY)))
    }.withOptions { qualifier(AppQualifiers.ANIME_QUOTES_VM) }

    viewModel {
        AstronomyInfoVM(get(_q(DMQualifiers.ASTRONOMY_INFO_REPOSITORY)))
    }.withOptions { qualifier(AppQualifiers.ASTRONOMY_INFO_VM) }
}