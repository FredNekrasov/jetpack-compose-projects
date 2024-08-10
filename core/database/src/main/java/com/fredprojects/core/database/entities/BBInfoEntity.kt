package com.fredprojects.core.database.entities

import androidx.room.*

/**
 * BBInfoEntity is used to store data in the database.
 * @param title is the title of the product
 * @param type is the type of the product
 * @param tags is the tags of the product
 * @param dateTime is the date and time of the product
 * @param description is the description of the product
 * @param endDateTime is the end date and time of the product
 * @param startDateTime is the start date and time of the product
 * @param url is the url of the product
 * @param favorite is the favorite of the product
 * @param id is the id of the product
 */
@Entity(tableName = "bybit")
data class BBInfoEntity(
    val title: String,
    val type: String,
    val tags: String,
    @ColumnInfo(name = "date_time")
    val dateTime: String,
    val description: String,
    @ColumnInfo(name = "end_date_time")
    val endDateTime: String,
    @ColumnInfo(name = "start_date_time")
    val startDateTime: String,
    val url: String,
    val favorite: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)