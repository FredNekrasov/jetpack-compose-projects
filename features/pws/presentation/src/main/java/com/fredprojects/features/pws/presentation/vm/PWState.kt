package com.fredprojects.features.pws.presentation.vm

import androidx.compose.runtime.Stable
import com.fredprojects.features.pws.domain.utils.SortType
import com.fredprojects.features.pws.domain.utils.SortingPW
import com.fredprojects.features.pws.presentation.models.PWPModel

/**
 * PWState represents the state of the practical works.
 * @param pws the list of practical works
 * @param sortingPW the sorting type of the practical works
 * @param isSortingSectionVisible the visibility of the sorting section
 */
@Stable
data class PWState(
    @Stable
    val pws: List<PWPModel> = emptyList(),
    val sortingPW: SortingPW = SortingPW.Date(SortType.Descending),
    val isSortingSectionVisible: Boolean = false
)