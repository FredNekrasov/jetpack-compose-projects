package com.fredprojects.helloworld.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fredprojects.helloworld.data.local.dao.*
import com.fredprojects.helloworld.data.local.entities.*

/**
 * The HelloWorldDb is used to store the data for the app.
 * The database contains the following entities:
 *  - [JDEntity]
 *  - [MathEntity]
 *  - [PWEntity]
 *  - [AstronomyInfoEntity]
 *  @property astronomyInfoDao the dao for the [AstronomyInfoEntity]. See [IAstronomyInfoDao]
 *  @property mathDao the dao for the [MathEntity]. See [IMathDao]
 *  @property jdDao the dao for the [JDEntity]. See [IJDDao]
 *  @property pwDao the dao for the [PWEntity]. See [IPWDao]
 */
@Database(
    entities = [PWEntity::class, JDEntity::class, MathEntity::class, AstronomyInfoEntity::class],
    version = 2,
    exportSchema = false
)
abstract class HelloWorldDb : RoomDatabase() {
    abstract val astronomyInfoDao: IAstronomyInfoDao
    abstract val mathDao: IMathDao
    abstract val jdDao: IJDDao
    abstract val pwDao: IPWDao
    companion object {
        const val DB_NAME = "HelloWorldDb.db"
    }
}