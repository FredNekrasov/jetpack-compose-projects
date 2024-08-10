package com.fredprojects.features.auth.domain.useCases

import com.fredprojects.features.auth.domain.models.User
import com.fredprojects.features.auth.domain.repository.IUserRepository
import com.fredprojects.features.auth.domain.utils.*

/**
 * AuthUseCase is used to authenticate a User
 * @param repository is the repository used to authenticate the User
 */
class AuthUseCase(
    private val repository: IUserRepository
) {
    /**
     * invoke is used to authenticate a User
     * @param login is the login of the User
     * @param password is the password of the User
     * @return Pair<AuthStatus, User?>
     */
    suspend operator fun invoke(login: String, password: String): Pair<AuthStatus, User?> {
        return when {
            !isUserNameValid(login) -> AuthStatus.INVALID_USERNAME to null
            !isPasswordValid(password) -> AuthStatus.INVALID_PASSWORD to null
            else -> {
                val user = repository.login(login, password) ?: return AuthStatus.INVALID_DATA to null
                AuthStatus.SUCCESS to user
            }
        }
    }
}