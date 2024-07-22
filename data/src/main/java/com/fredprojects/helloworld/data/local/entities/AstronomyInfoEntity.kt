package com.fredprojects.helloworld.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * AstronomyInfoEntity is used to store data of the astronomy information.
 * "astronomy" is the table name in the database.
 */
@Entity(tableName = "astronomy")
data class AstronomyInfoEntity(
    val dec: String,
    val name: String,
    val references: String,
    val ra: String,
    val radius: Float,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)