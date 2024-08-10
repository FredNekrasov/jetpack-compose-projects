package com.fredprojects.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fredprojects.core.database.dao.*
import com.fredprojects.core.database.entities.*

/**
 * The HelloWorldDb is used to store the data for the app.
 * The database contains the following entities:
 *  - [JDEntity]
 *  - [MathEntity]
 *  - [PWEntity]
 *  - [AstronomyInfoEntity]
 *  - [BBInfoEntity]
 *  - [UserEntity]
 *  @property astronomyInfoDao the dao for the [AstronomyInfoEntity]. See [IAstronomyInfoDao]
 *  @property mathDao the dao for the [MathEntity]. See [IMathDao]
 *  @property jdDao the dao for the [JDEntity]. See [IJDDao]
 *  @property pwDao the dao for the [PWEntity]. See [IPWDao]
 *  @property bbdDao the dao for the [BBInfoEntity]. See [IBBDao]
 *  @property userDao the dao for the [UserEntity]. See [IUserDao]
 */
@Database(
    entities = [PWEntity::class, JDEntity::class, MathEntity::class, AstronomyInfoEntity::class, BBInfoEntity::class, UserEntity::class],
    version = 2,
    exportSchema = false
)
abstract class HelloWorldDb : RoomDatabase() {
    abstract val astronomyInfoDao: IAstronomyInfoDao
    abstract val mathDao: IMathDao
    abstract val jdDao: IJDDao
    abstract val pwDao: IPWDao
    abstract val bbdDao: IBBDao
    abstract val userDao: IUserDao
    companion object {
        const val DB_NAME = "HelloWorldDb.db"
    }
}