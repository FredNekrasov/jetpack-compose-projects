package com.fredprojects.core.database.dao

import androidx.room.*
import com.fredprojects.core.database.entities.UserEntity

/**
 * IUserDao represents the interface for the user dao.
 * The user dao is used to get data from the server and save it in the database.
 */
@Dao
interface IUserDao {
    /**
     * get is used to get the user with the given username and password from the database.
     * @param login the username of the user
     * @param password the password of the user
     * @return the user with the given username and password
     */
    @Query("SELECT * FROM users WHERE username = :login AND password = :password")
    suspend fun get(login: String, password: String): UserEntity?
    /**
     * upsert is used to insert user into the database or update it if it already exists.
     * @param user the user to be inserted or updated in the database.
     */
    @Upsert
    suspend fun upsert(user: UserEntity)
    /**
     * delete is used to delete a user from the database by its id.
     * @param userId the id of the user to be deleted
     */
    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun delete(userId: Int)
}