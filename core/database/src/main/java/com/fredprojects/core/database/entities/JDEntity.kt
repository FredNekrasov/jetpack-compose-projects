package com.fredprojects.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * JDEntity is used to store data of the jumps in the database.
 * "jumps" is the name of the table in the database.
 */
@Entity(tableName = "jumps")
data class JDEntity(
    val count: Int,
    val date: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)