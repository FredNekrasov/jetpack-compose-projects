package com.fredprojects.core.database.entities

import androidx.room.*

@Entity(tableName = "users")
data class UserEntity(
    @ColumnInfo(name = "username")
    val login: String,
    val password: String,
    val email: String,
    val name: String,
    val surname: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)