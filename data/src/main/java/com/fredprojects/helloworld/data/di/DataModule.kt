package com.fredprojects.helloworld.data.di

import org.koin.dsl.module

val dataModule = module {
    includes(dataSourceModule, repositoryModule)
}