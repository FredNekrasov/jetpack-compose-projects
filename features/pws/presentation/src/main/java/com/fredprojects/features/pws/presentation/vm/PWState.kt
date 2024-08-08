package com.fredprojects.features.pws.presentation.vm

import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.domain.utils.SortType
import com.fredprojects.features.pws.domain.utils.SortingPW

/**
 * PWState represents the state of the practical works.
 * @param pws the list of practical works
 * @param sortingPW the sorting type of the practical works
 * @param isSortingSectionVisible the visibility of the sorting section
 */
data class PWState(
    val pws: List<PracticalWork> = emptyList(),
    val sortingPW: SortingPW = SortingPW.Date(SortType.Descending),
    val isSortingSectionVisible: Boolean = false
)