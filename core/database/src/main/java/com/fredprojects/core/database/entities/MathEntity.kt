package com.fredprojects.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * MathEntity represents an entity in the database.
 * "math" is the name of the table in the database.
 */
@Entity(tableName = "math")
data class MathEntity(
    val expression: String,
    val result: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)