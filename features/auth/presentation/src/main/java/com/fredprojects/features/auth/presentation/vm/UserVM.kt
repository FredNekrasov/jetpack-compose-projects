package com.fredprojects.features.auth.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.features.auth.domain.models.User
import com.fredprojects.features.auth.domain.useCases.UserUseCases
import com.fredprojects.features.auth.domain.utils.AuthStatus
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserVM(
    private val useCases: UserUseCases
) : ViewModel() {
    private val authStatusMSF = MutableSharedFlow<AuthStatus>()
    val authStatusSF = authStatusMSF.asSharedFlow()
    private val userDataMSF = MutableStateFlow<User?>(null)
    val userDataSF = userDataMSF.asStateFlow()
    fun onUserEvent(event : AuthEvents) {
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
                userDataMSF.emit(it.second)
            }
        }
    }
    private fun upsertUserData(user: User) {
        viewModelScope.launch {
            val (authStatus, userData) = useCases.upsert(user)
            authStatusMSF.emit(authStatus)
            userDataMSF.emit(userData)
        }
    }
    private fun delete() {
        viewModelScope.launch {
            userDataSF.value?.id?.let {
                useCases.delete(it)
            }
        }
    }
}