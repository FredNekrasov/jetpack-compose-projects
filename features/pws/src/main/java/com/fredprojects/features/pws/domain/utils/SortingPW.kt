package com.fredprojects.features.pws.domain.utils

/**
 * SortingPW is used to sort the data.
 * @param sortType the type of sorting
 * @property PW if the data should be sorted by PW name
 * @property Student if the data should be sorted by student name
 * @property Variant if the data should be sorted by variant
 * @property LVL if the data should be sorted by level
 * @property Date if the data should be sorted by date
 * @property Mark if the data should be sorted by mark
 */
sealed class SortingPW(val sortType: SortType) {
    class PW(sortType: SortType) : SortingPW(sortType)
    class Student(sortType: SortType) : SortingPW(sortType)
    class Variant(sortType: SortType) : SortingPW(sortType)
    class LVL(sortType: SortType) : SortingPW(sortType)
    class Date(sortType: SortType) : SortingPW(sortType)
    class Mark(sortType: SortType) : SortingPW(sortType)

    fun copy(sortType: SortType): SortingPW = when(this) {
        is Date -> Date(sortType)
        is LVL -> LVL(sortType)
        is Mark -> Mark(sortType)
        is PW -> PW(sortType)
        is Student -> Student(sortType)
        is Variant -> Variant(sortType)
    }
}