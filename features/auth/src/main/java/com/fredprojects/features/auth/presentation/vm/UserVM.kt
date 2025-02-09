package com.fredprojects.features.auth.presentation.vm

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.features.auth.domain.models.User
import com.fredprojects.features.auth.domain.useCases.UserUseCases
import com.fredprojects.features.auth.domain.utils.AuthStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserVM @Inject constructor(
    private val useCases: UserUseCases
) : ViewModel() {
    private val authStatusMSF = MutableSharedFlow<AuthStatus>()
    val authStatusSF = authStatusMSF.asSharedFlow()
    var authState by mutableStateOf<User?>(null)
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
                authState = it.second
            }
        }
    }
    private fun upsertUserData(user: User) {
        viewModelScope.launch {
            val (authStatus, userData) = useCases.upsert(user)
            authStatusMSF.emit(authStatus)
            authState = userData
        }
    }
    private fun delete() {
        viewModelScope.launch {
            authState?.id?.let { useCases.delete(it) }
            authState = null
        }
    }
}