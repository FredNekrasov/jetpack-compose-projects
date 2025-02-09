package com.fredprojects.features.jump.presentation.menu.vm

import androidx.compose.runtime.Stable
import com.fredprojects.features.jump.domain.models.JumpData
import com.fredprojects.features.jump.domain.utils.SortType

/**
 * JDState is used to store data of the jumps
 * @param jds the list of jump data that will be displayed in the list
 * @param jd the jump data that will be displayed in the dialog and will be edited
 * @param sortType the sort type of the jump data list by date
 * @param isSortingSectionVisible the visibility of the sorting section
 * @param isDialogVisible the visibility of the dialog
 */
@Stable
data class JDState(
    @Stable
    val jds: List<JumpData> = emptyList(),
    val jd: JumpData? = null,
    val sortType: SortType = SortType.Descending,
    val isSortingSectionVisible: Boolean = false,
    val isDialogVisible: Boolean = false
)