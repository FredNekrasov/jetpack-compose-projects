package com.fredprojects.features.auth.domain.repository

import com.fredprojects.features.auth.domain.models.User

/**
 * IUserRepository represents the interface for the repository.
 * The repository is used to write and read data from the database.
 */
interface IUserRepository {
    /**
     * login is used to log in a user.
     * @param login the login of the user
     * @param password the password of the user
     * @return the user if the login is successful, null otherwise
     */
    suspend fun login(login: String, password: String): User?
    /**
     * upsert is used to register a new user in the database or update an existing one.
     * @param userData the user to insert or update
     */
    suspend fun upsert(userData: User)
    /**
     * delete is used to delete a user.
     * @param userId the id of the user to delete
     */
    suspend fun delete(userId: Int)
}