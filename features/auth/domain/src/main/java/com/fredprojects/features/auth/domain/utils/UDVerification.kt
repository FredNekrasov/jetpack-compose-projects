package com.fredprojects.features.auth.domain.utils

// UserDataVerification
internal fun isUserNameValid(login: String): Boolean = login.isNotBlank()
internal fun isPasswordValid(password: String): Boolean = password.isNotBlank() && password.length >= 6
internal fun isEmailValid(email: String): Boolean = email.isNotBlank() && email.contains("@")