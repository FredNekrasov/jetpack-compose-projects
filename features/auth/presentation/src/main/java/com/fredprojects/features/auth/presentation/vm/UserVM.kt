package com.fredprojects.features.auth.presentation.vm

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.features.auth.domain.useCases.UserUseCases
import com.fredprojects.features.auth.domain.utils.AuthStatus
import com.fredprojects.features.auth.presentation.mappers.*
import com.fredprojects.features.auth.presentation.models.UDPModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserVM(
    private val useCases: UserUseCases
) : ViewModel() {
    private val authStatusMSF = MutableSharedFlow<AuthStatus>()
    val authStatusSF = authStatusMSF.asSharedFlow()
    var authState by mutableStateOf<UDPModel?>(null)
        private set
    fun onEvent(event : AuthEvents) {
        when(event) {
            is AuthEvents.Authorization -> auth(event.login, event.password)
            AuthEvents.DeleteUser -> delete()
            is AuthEvents.UpsertUserData -> upsertUserData(event.user)
        }
    }
    private fun auth(userName: String, password: String) {
        viewModelScope.launch {
            useCases.auth(userName,password).also {
                authStatusMSF.emit(it.first)
                authState = it.second?.toPresentation()
            }
        }
    }
    private fun upsertUserData(user: UDPModel) {
        viewModelScope.launch {
            val (authStatus, userData) = useCases.upsert(user.toDomain())
            authStatusMSF.emit(authStatus)
            authState = userData?.toPresentation()
        }
    }
    private fun delete() {
        viewModelScope.launch {
            authState?.id?.let { useCases.delete(it) }
            authState = null
        }
    }
}