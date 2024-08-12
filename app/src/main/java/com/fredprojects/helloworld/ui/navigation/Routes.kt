package com.fredprojects.helloworld.ui.navigation

import androidx.compose.runtime.mutableStateListOf
import com.fredprojects.core.ui.R

object Routes {
    const val AUTH = "auth"
    const val REGISTRATION = "registration"
    const val PROFILE = "profile"

    const val MAIN_SCREEN = "main screen"

    const val FIBONACCI = "fibonacci"
    const val RESULT_FIB = "result of fibonacci sequence"

    const val PW_LIST = "list of practical works"
    const val UPSERT_PW = "upsert a practical work"

    const val JD_LIST = "jumps"
    const val JUMPING_ROPE = "jumping rope"

    const val BYBIT = "bybit"
    const val FAV_PRODUCTS = "favorite products"

    val mainScreenItems = mutableStateListOf(R.string.inequality, R.string.math, R.string.astronomyInfo)
}