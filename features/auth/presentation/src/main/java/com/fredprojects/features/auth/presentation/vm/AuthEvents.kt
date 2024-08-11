package com.fredprojects.features.auth.presentation.vm

import com.fredprojects.features.auth.domain.models.User

sealed class AuthEvents {
    data class Authorization(val login: String, val password: String) : AuthEvents()
    data class UpsertUserData(val user: User) : AuthEvents()
    data object DeleteUser : AuthEvents()
}