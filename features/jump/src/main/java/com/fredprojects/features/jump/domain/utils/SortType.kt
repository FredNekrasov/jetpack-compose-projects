package com.fredprojects.features.jump.domain.utils

sealed class SortType {
    data object Ascending : SortType()
    data object Descending : SortType()
}