package com.fredprojects.features.inequality.api.useCases

import com.fredprojects.features.inequality.api.utils.InequalityStatus

/**
 * InequalityUseCase represents the use case for solving inequality in the form of ax + b < 0
 */
class InequalityUseCase {
    /**
     * Invoke the use case to solve the inequality of the form ax + b < 0
     * @param a the first coefficient
     * @param b the second coefficient
     * @return the status of the solution
     */
    operator fun invoke(a: Float?, b: Float?) = when {
        b == null || a == null -> InequalityStatus.ERROR
        (b == 0f && a == 0f) || (a == 0f && b > 0f) -> InequalityStatus.NO_SOLUTION
        a == 0f && b < 0f -> InequalityStatus.FIRST_SOLUTION
        a < 0 -> InequalityStatus.SECOND_SOLUTION
        else -> InequalityStatus.THIRD_SOLUTION
    }
}