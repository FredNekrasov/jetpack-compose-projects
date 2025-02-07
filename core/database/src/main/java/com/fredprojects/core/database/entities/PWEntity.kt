package com.fredprojects.core.database.entities

import androidx.room.*

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