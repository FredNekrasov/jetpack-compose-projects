package com.fredprojects.features.auth.domain.models

/**
 * User model used to store user data
 * @param login is the username
 * @param password is the password
 * @param email is the email
 * @param name is the name
 * @param surname is the surname
 * @param id is the id
 */
data class User(
    val login: String,
    val password: String,
    val email: String,
    val name: String,
    val surname: String,
    val id: Int? = null
)