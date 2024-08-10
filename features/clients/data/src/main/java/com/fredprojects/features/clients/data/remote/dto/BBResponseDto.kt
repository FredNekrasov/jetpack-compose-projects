package com.fredprojects.features.clients.data.remote.dto

data class BBResponseDto(
    val result: BBResultDto,
    val retCode: Int,
    val retMsg: String,
    val time: Long
)