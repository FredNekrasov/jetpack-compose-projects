package com.fredprojects.helloworld.di

import com.fredprojects.helloworld.data.di.dataModule
import org.koin.dsl.module

val appModule = module {
    includes(dataModule, domainModule, presentationModule)
}