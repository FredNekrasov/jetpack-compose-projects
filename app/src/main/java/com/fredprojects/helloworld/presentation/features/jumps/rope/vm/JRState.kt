package com.fredprojects.helloworld.presentation.features.jumps.rope.vm

import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import com.fredprojects.helloworld.domain.features.jumps.utils.JumpStatus

/**
 * JRState is used to store the state of the rope jump
 * @param jumpData the training data
 * @param status the status of the record
 * @param isShowDialog the visibility of the dialog
 */
data class JRState(
    val jumpData: JumpData = JumpData(0),
    val status: JumpStatus = JumpStatus.SUCCESS,
    val isShowDialog: Boolean = false
)