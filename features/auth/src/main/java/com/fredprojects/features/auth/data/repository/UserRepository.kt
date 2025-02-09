package com.fredprojects.features.auth.data.repository

import com.fredprojects.core.database.dao.IUserDao
import com.fredprojects.features.auth.data.mapper.*
import com.fredprojects.features.auth.domain.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val dao: IUserDao
) {
    /**
     * login is used to log in a user.
     * @param login the login of the user
     * @param password the password of the user
     * @return the user if the login is successful, null otherwise
     */
    suspend fun login(login: String, password: String): User? = dao.get(login, password)?.toDomain()
    /**
     * upsert is used to register a new user in the database or update an existing one.
     * @param userData the user to insert or update
     */
    suspend fun upsert(userData: User) = dao.upsert(userData.toEntity())
    suspend fun delete(userId: Int) = dao.delete(userId)
}