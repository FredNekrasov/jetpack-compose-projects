package com.fredprojects.features.auth.domain.models

data class User(
    val login: String,
    val password: String,
    val email: String,
    val name: String,
    val surname: String,
    val id: Int? = null
)