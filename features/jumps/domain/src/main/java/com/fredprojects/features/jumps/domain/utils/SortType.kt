package com.fredprojects.features.jumps.domain.utils

/**
 * Sort type
 * @property Ascending if the data should be sorted in ascending order
 * @property Descending if the data should be sorted in descending order
 */
sealed class SortType {
    data object Ascending : SortType()
    data object Descending : SortType()
}