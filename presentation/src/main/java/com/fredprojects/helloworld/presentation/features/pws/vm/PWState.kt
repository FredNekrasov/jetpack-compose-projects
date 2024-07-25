package com.fredprojects.helloworld.presentation.features.pws.vm

import com.fredprojects.helloworld.domain.core.utils.SortType
import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import com.fredprojects.helloworld.domain.features.pws.utils.SortingPW

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