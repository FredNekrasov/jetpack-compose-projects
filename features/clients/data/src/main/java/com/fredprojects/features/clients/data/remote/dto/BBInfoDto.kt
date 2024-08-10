package com.fredprojects.features.clients.data.remote.dto

import com.fredprojects.features.clients.domain.bybit.models.BBType

data class BBInfoDto(
    val dateTimestamp: Long,
    val description: String,
    val endDateTimestamp: Long,
    val startDateTimestamp: Long,
    val tags: List<String>,
    val title: String,
    val type: BBType,
    val url: String
)