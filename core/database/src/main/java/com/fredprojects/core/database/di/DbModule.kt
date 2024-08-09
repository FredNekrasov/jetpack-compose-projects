package com.fredprojects.core.database.di

import androidx.room.Room
import com.fredprojects.core.database.HelloWorldDb
import com.fredprojects.core.database.dao.*
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val dbModule = module {
    single(qualifier<HelloWorldDb>(), createdAtStart = true) {
        Room.databaseBuilder(get(), HelloWorldDb::class.java, HelloWorldDb.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    single(qualifier<IAstronomyInfoDao>()) {
        get<HelloWorldDb>(qualifier<HelloWorldDb>()).astronomyInfoDao
    }
    single(qualifier<IJDDao>()) {
        get<HelloWorldDb>(qualifier<HelloWorldDb>()).jdDao
    }
    single(qualifier<IMathDao>()) {
        get<HelloWorldDb>(qualifier<HelloWorldDb>()).mathDao
    }
    single(qualifier<IPWDao>()) {
        get<HelloWorldDb>(qualifier<HelloWorldDb>()).pwDao
    }
}