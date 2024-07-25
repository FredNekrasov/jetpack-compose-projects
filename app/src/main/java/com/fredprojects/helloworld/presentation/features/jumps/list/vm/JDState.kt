package com.fredprojects.helloworld.presentation.features.jumps.list.vm

import com.fredprojects.helloworld.domain.core.utils.SortType
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import com.fredprojects.helloworld.domain.features.jumps.utils.JumpStatus

/**
 * JDState is used to store data of the jumps
 * @param jds the list of jump data that will be displayed in the list
 * @param jd the jump data that will be displayed in the dialog and will be edited
 * @param jdStatus the status of the jump data
 * @param sortType the sort type of the jump data list by date
 * @param isSortingSectionVisible the visibility of the sorting section
 * @param isShowDialog the visibility of the dialog
 */
data class JDState(
    val jds: List<JumpData> = emptyList(),
    val jd: JumpData? = null,
    val jdStatus: JumpStatus = JumpStatus.SUCCESS,
    val sortType: SortType = SortType.Descending,
    val isSortingSectionVisible: Boolean = false,
    val isShowDialog: Boolean = false
)