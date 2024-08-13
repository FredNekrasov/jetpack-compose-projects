package com.fredprojects.core.database.di

import android.content.Context
import androidx.room.Room
import com.fredprojects.core.database.HelloWorldDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DbModule {
    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context) = Room.databaseBuilder(context, HelloWorldDb::class.java, HelloWorldDb.DB_NAME)
        .fallbackToDestructiveMigration()
        .build()
    @Provides
    fun provideAstronomyInfoDao(db: HelloWorldDb) = db.astronomyInfoDao
    @Provides
    fun provideJDDao(db: HelloWorldDb) = db.jdDao
    @Provides
    fun provideMathDao(db: HelloWorldDb) = db.mathDao
    @Provides
    fun providePWDao(db: HelloWorldDb) = db.pwDao
    @Provides
    fun provideBBDao(db: HelloWorldDb) = db.bbdDao
    @Provides
    fun provideUserDao(db: HelloWorldDb) = db.userDao
}