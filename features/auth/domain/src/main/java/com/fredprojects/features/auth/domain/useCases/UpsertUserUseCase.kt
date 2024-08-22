package com.fredprojects.features.auth.domain.useCases

import com.fredprojects.features.auth.domain.models.User
import com.fredprojects.features.auth.domain.repository.IUserRepository
import com.fredprojects.features.auth.domain.utils.*

/**
 * UpsertUserUseCase is used to register or update a User data
 * @param repository is the repository used to insert or update the User
 */
class UpsertUserUseCase(
    private val repository: IUserRepository
) {
    /**
     * invoke is used to insert or update a User data
     * @param userData is the User data
     * @return Pair<AuthStatus, User?>
     */
    suspend operator fun invoke(userData: User): Pair<AuthStatus, User?> = when {
        !isUserNameValid(userData.login) -> Pair(AuthStatus.INVALID_USERNAME, null)
        !isPasswordValid(userData.password) -> Pair(AuthStatus.INVALID_PASSWORD, null)
        !isEmailValid(userData.email) -> Pair(AuthStatus.INVALID_EMAIL, null)
        else -> {
            val existingUser = repository.login(userData.login, userData.password)
            if(existingUser == null || userData.id != null) {
                repository.upsert(userData)
                val newUser = repository.login(userData.login, userData.password)
                Pair(AuthStatus.SUCCESS, newUser)
            } else Pair(AuthStatus.EXISTING_DATA, null)
        }
    }
}