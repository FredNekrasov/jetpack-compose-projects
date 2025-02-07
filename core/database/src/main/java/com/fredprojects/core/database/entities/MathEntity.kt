package com.fredprojects.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "math")
data class MathEntity(
    val expression: String,
    val result: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)