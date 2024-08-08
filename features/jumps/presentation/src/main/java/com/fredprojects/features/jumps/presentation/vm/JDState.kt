package com.fredprojects.features.jumps.presentation.vm

import com.fredprojects.features.jumps.domain.models.JumpData
import com.fredprojects.features.jumps.domain.utils.JumpStatus
import com.fredprojects.features.jumps.domain.utils.SortType

/**
 * JDState is used to store data of the jumps
 * @param jds the list of jump data that will be displayed in the list
 * @param jd the jump data that will be displayed in the dialog and will be edited
 * @param jdStatus the status of the jump data
 * @param sortType the sort type of the jump data list by date
 * @param isSortingSectionVisible the visibility of the sorting section
 */
data class JDState(
    val jds: List<JumpData> = emptyList(),
    val jd: JumpData? = null,
    val jdStatus: JumpStatus = JumpStatus.NOTHING,
    val sortType: SortType = SortType.Descending,
    val isSortingSectionVisible: Boolean = false
)