package com.fredprojects.helloworld.data.di

import androidx.room.Room
import com.fredprojects.helloworld.data.local.HelloWorldDb
import com.fredprojects.helloworld.data.remote.*
import org.koin.core.module.dsl.withOptions
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

internal val dataSourceModule = module {
    single(createdAtStart = true) {
        Room.databaseBuilder(get(), HelloWorldDb::class.java, HelloWorldDb.DB_NAME).build()
    }.withOptions { qualifier = qualifier(DMQualifiers.HELLO_WORLD_DB) }
    single(createdAtStart = true) { createAQService() }.withOptions {
        qualifier = qualifier(DMQualifiers.AQ_SERVICE)
    }
    single(createdAtStart = true) { createMathService() }.withOptions {
        qualifier = qualifier(DMQualifiers.MATH_SERVICE)
    }
    single(createdAtStart = true) { createAstronomyInfoService() }.withOptions {
        qualifier = qualifier(DMQualifiers.ASTRONOMY_INFO_SERVICE)
    }
}