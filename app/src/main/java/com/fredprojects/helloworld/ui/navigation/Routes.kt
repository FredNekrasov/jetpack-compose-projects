package com.fredprojects.helloworld.ui.navigation

import androidx.compose.runtime.mutableStateListOf
import com.fredprojects.core.ui.R

object Routes {
    const val AUTH = "auth"
    const val REGISTRATION = "registration"
    const val PROFILE = "profile"

    const val MAIN_SCREEN = "mainScreen"

    const val FIBONACCI = "fibonacci"
    const val RESULT_FIB = "resultOfFibonacciSequence"

    const val PW_LIST = "listOfPracticalWorks"
    const val UPSERT_PW = "upsertPracticalWork"

    const val JD_LIST = "jumps"
    const val JUMPING_ROPE = "jumpingRope"

    const val BYBIT = "bybit"
    const val FAV_PRODUCTS = "favoriteProducts"

    val mainScreenItems = mutableStateListOf(R.string.inequality, R.string.math, R.string.astronomyInfo)
}