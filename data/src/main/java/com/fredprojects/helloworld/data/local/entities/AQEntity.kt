package com.fredprojects.helloworld.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * AnimeQuoteEntity represents an entity in the database.
 * "anime" is the name of the table in the database.
 */
@Entity(tableName = "anime_quotes")
data class AQEntity(
    val anime: String,
    val character: String,
    val quote: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)