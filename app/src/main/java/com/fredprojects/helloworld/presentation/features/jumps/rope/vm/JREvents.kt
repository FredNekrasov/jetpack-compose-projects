package com.fredprojects.helloworld.presentation.features.jumps.rope.vm

/**
 * JREvents is used to send events to the view model
 * @property InsertJD the event for inserting training data
 * @property SwitchingDialog the event for switching the visibility of the dialog
 */
sealed class JREvents {
    data class InsertJD(val countOfJumps: Int) : JREvents()
    data object SwitchingDialog : JREvents()
}