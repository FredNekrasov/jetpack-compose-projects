package com.fredprojects.helloworld.data.local.entities

import androidx.room.*

/**
 * PWEntity is used to store data of the practical works in the database.
 * "practical_works" is the name of the table in the database.
 */
@Entity(tableName = "practical_works")
data class PWEntity(
    @ColumnInfo(name = "pw_name")
    val pwName: String,
    val student: String,
    val variant: Int,
    val level: Int,
    val date: String,
    val mark: Int,
    val image: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)