package com.fredprojects.features.jumps.presentation.vm

import androidx.compose.runtime.Stable
import com.fredprojects.features.jumps.domain.models.JumpData
import com.fredprojects.features.jumps.domain.utils.SortType

/**
 * JDEvents is used to handle events in the JumpDataList
 * @property Sort the sort type of the list that should be sorted in ascending or descending order by date
 * @property DeleteJD the data that should be deleted
 * @property UpsertJD the data that should be inserted or updated
 * @property GetJD the id of the data that should be edited
 * @property ToggleSortSection the visibility of the sorting section
 * @property SwitchingDialog the visibility of the dialog
 */
sealed class JDEvents {
    @Stable
    data class Sort(val sortType: SortType) : JDEvents()
    @Stable
    data class DeleteJD(val jumpData: JumpData) : JDEvents()
    @Stable
    data class UpsertJD(val jumpData: JumpData) : JDEvents()
    @Stable
    data class GetJD(val jumpData: JumpData) : JDEvents()
    data object ToggleSortSection : JDEvents()
    data object SwitchingDialog : JDEvents()
}