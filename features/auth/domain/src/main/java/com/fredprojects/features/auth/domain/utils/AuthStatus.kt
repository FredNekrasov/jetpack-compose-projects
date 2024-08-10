package com.fredprojects.features.auth.domain.utils

/**
 * AuthStatus represents the status of the user data
 *
 * @property SUCCESS the status of the user data is success
 * @property EXISTING_DATA the status of the user data is existing data
 * @property INVALID_DATA the status of the user data is invalid data
 * @property INVALID_USERNAME the status of the user data is invalid username
 * @property INVALID_PASSWORD the status of the user data is invalid password
 * @property INVALID_EMAIL the status of the user data is invalid email
 * @property NOTHING the status of the user data is nothing
 */
enum class AuthStatus {
    SUCCESS,
    EXISTING_DATA,
    INVALID_DATA,
    INVALID_USERNAME,
    INVALID_PASSWORD,
    INVALID_EMAIL,
    NOTHING
}