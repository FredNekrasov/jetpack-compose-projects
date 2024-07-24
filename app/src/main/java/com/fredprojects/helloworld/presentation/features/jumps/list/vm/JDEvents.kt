package com.fredprojects.helloworld.presentation.features.jumps.list.vm

import com.fredprojects.helloworld.domain.core.utils.SortType
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData

sealed class JDEvents {
    data class Sort(val sortType: SortType) : JDEvents()
    data class DeleteJD(val jumpData: JumpData) : JDEvents()
    data class UpsertJD(val jumpData: JumpData) : JDEvents()
    data class GetJD(val jumpData: JumpData) : JDEvents()
    data object ToggleSortSection : JDEvents()
    data object SwitchingDialog : JDEvents()
}