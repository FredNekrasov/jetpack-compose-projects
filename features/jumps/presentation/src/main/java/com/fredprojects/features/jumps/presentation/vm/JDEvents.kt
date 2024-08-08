package com.fredprojects.features.jumps.presentation.vm

import com.fredprojects.features.jumps.domain.models.JumpData
import com.fredprojects.features.jumps.domain.utils.SortType

/**
 * JDEvents is used to handle events in the JumpDataList
 * @property Sort the sort type of the list that should be sorted in ascending or descending order by date
 * @property DeleteJD the data that should be deleted
 * @property UpsertJD the data that should be inserted or updated
 * @property GetJD the id of the data that should be edited
 * @property ToggleSortSection the visibility of the sorting section
 */
sealed class JDEvents {
    data class Sort(val sortType: SortType) : JDEvents()
    data class DeleteJD(val jumpData: JumpData) : JDEvents()
    data class UpsertJD(val jumpData: JumpData) : JDEvents()
    data class GetJD(val jumpData: JumpData) : JDEvents()
    data object ToggleSortSection : JDEvents()
}