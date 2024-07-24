package com.fredprojects.helloworld.presentation.features.jumps.rope.vm

sealed class JREvents {
    data class InsertJD(val countOfJumps: Int) : JREvents()
    data object SwitchingDialog : JREvents()
}