package com.fredprojects.helloworld.data.local

import android.app.Application
import androidx.room.Room

/**
 * DatabaseBuilder is used to create an instance of the database.
 */
fun createDb(context: Application) = Room.databaseBuilder(
    context, HelloWorldDb::class.java, HelloWorldDb.DB_NAME
).build()