package com.fredprojects.helloworld.presentation.features.pws.vm

import com.fredprojects.helloworld.domain.core.utils.SortType
import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import com.fredprojects.helloworld.domain.features.pws.utils.SortingPW

data class PWState(
    val pws: List<PracticalWork> = emptyList(),
    val sortingPW: SortingPW = SortingPW.Date(SortType.Descending),
    val isSortingSectionVisible: Boolean = false
)