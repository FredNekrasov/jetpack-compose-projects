package com.fredprojects.features.clients.domain.bybit.models

/**
 * Bybit data class used to store product data
 * @param title the title of the product
 * @param type the type of the product
 * @param dateTime the date and time of the product
 * @param description the description of the product
 * @param endDateTime the end date and time of the product
 * @param startDateTime the start date and time of the product
 * @param tags the tags of the product
 * @param url the url of the product
 * @param favorite the favorite status of the product
 * @param id the id of the product
 */
data class BBInfo(
    val title: String,
    val type: BBType,
    val dateTime: String,
    val description: String,
    val endDateTime: String,
    val startDateTime: String,
    val tags: List<String>,
    val url: String,
    var favorite: Boolean = false,
    val id: Int? = null
)