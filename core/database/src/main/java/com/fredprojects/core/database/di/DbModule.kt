package com.fredprojects.core.database.di

import androidx.room.Room
import com.fredprojects.core.database.HelloWorldDb
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val dbModule = module {
    single(qualifier = qualifier<HelloWorldDb>(), createdAtStart = true) {
        Room.databaseBuilder(get(), HelloWorldDb::class.java, HelloWorldDb.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}