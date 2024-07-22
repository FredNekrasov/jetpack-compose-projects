package com.fredprojects.helloworld.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fredprojects.helloworld.data.local.dao.*
import com.fredprojects.helloworld.data.local.entities.*

@Database(
    entities = [PWEntity::class, JDEntity::class, MathEntity::class, AQEntity::class, AstronomyInfoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class HelloWorldDb : RoomDatabase() {
    abstract val aqDao: IAQDao
    abstract val astronomyInfoDao: IAstronomyInfoDao
    abstract val mathDao: IMathDao
    abstract val jdDao: IJDDao
    abstract val pwDao: IPWDao
    companion object {
        const val DB_NAME = "HelloWorldDb.db"
    }
}