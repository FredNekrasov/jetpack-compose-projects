package com.fredprojects.core.database.entities

import androidx.room.*

/**
 * UserEntity is used to store data of the users in the database.
 * "users" is the name of the table in the database.
 */
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