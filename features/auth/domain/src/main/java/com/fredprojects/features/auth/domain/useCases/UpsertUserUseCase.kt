package com.fredprojects.features.auth.domain.useCases

import com.fredprojects.features.auth.domain.models.User
import com.fredprojects.features.auth.domain.repository.IUserRepository
import com.fredprojects.features.auth.domain.utils.*

class UpsertUserUseCase(
    private val repository: IUserRepository
) {
    suspend operator fun invoke(userData: User): Pair<AuthStatus, User?> = when {
        !isUserNameValid(userData.login) -> Pair(AuthStatus.INVALID_USERNAME, null)
        !isPasswordValid(userData.password) -> Pair(AuthStatus.INVALID_PASSWORD, null)
        !isEmailValid(userData.email) -> Pair(AuthStatus.INVALID_EMAIL, null)
        else -> {
            val user = repository.login(userData.login, userData.password)
            if(user == null) {
                repository.upsert(userData)
                Pair(AuthStatus.SUCCESS, userData)
            } else Pair(AuthStatus.EXISTING_DATA, null)
        }
    }
}