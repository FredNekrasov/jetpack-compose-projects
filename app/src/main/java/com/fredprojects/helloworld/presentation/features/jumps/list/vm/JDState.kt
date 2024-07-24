package com.fredprojects.helloworld.presentation.features.jumps.list.vm

import com.fredprojects.helloworld.domain.core.utils.SortType
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import com.fredprojects.helloworld.domain.features.jumps.utils.JumpStatus

data class JDState(
    val jds: List<JumpData> = emptyList(),
    val jd: JumpData? = null,
    val jdStatus: JumpStatus = JumpStatus.SUCCESS,
    val sortType: SortType = SortType.Descending,
    val isSortingSectionVisible: Boolean = false,
    val isShowDialog: Boolean = false
)