package com.fredprojects.features.auth.presentation.vm

import com.fredprojects.features.auth.presentation.models.UDPModel

sealed class AuthEvents {
    data class Authorization(val login: String, val password: String) : AuthEvents()
    data class UpsertUserData(val user: UDPModel) : AuthEvents()
    data object DeleteUser : AuthEvents()
}