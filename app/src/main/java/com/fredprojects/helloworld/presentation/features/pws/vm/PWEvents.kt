package com.fredprojects.helloworld.presentation.features.pws.vm

import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import com.fredprojects.helloworld.domain.features.pws.utils.SortingPW

sealed class PWEvents {
    data class Sort(val sortingPW: SortingPW) : PWEvents()
    data class DeletePW(val pw: PracticalWork) : PWEvents()
    data class SearchPW(val value: String) : PWEvents()
    data object RestorePW : PWEvents()
    data object ToggleSortSection : PWEvents()
}