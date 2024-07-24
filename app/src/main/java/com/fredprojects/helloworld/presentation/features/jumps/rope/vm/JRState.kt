package com.fredprojects.helloworld.presentation.features.jumps.rope.vm

import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import com.fredprojects.helloworld.domain.features.jumps.utils.JumpStatus

data class JRState(
    val jumpData: JumpData = JumpData(0),
    val status: JumpStatus = JumpStatus.SUCCESS,
    val isShowDialog: Boolean = false
)