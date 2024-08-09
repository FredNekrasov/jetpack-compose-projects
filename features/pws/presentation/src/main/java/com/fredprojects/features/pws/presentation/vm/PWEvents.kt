package com.fredprojects.features.pws.presentation.vm

import androidx.compose.runtime.Stable
import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.domain.utils.SortingPW

/**
 * PWEvents is used to send events to the PWListVM to perform actions
 * @property Sort used to sort the data by column
 * @property DeletePW used to delete the practical work from the database
 * @property SearchPW used to search for the practical work
 * @property RestorePW used to restore the deleted practical work
 * @property ToggleSortSection used to switch the visibility of the sorting section
 */
sealed class PWEvents {
    @Stable
    data class Sort(val sortingPW: SortingPW) : PWEvents()
    @Stable
    data class DeletePW(val pw: PracticalWork) : PWEvents()
    data class SearchPW(val value: String) : PWEvents()
    data object RestorePW : PWEvents()
    data object ToggleSortSection : PWEvents()
}