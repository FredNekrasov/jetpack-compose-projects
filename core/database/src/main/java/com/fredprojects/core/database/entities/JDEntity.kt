package com.fredprojects.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jumps")
data class JDEntity(
    val count: Int,
    val date: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)