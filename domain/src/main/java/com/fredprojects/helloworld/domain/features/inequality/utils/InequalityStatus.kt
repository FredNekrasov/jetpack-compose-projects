package com.fredprojects.helloworld.domain.features.inequality.utils

/**
 * InequalityStatus represents the status of the inequality solution
 * @property ERROR if an error has occurred during the InequalityUseCase execution or if the input is not valid
 * @property NO_SOLUTION if there is no solution
 * @property FIRST_SOLUTION if there is first type of solution
 * @property SECOND_SOLUTION if there is second type of solution
 * @property THIRD_SOLUTION if there is third type of solution
 */
enum class InequalityStatus {
    ERROR,
    NO_SOLUTION,
    FIRST_SOLUTION,
    SECOND_SOLUTION,
    THIRD_SOLUTION
}