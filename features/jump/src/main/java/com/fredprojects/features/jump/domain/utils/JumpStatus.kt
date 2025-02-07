package com.fredprojects.features.jump.domain.utils

/**
 * JumpStatus represents the status of the JumpData
 * @property SUCCESS if the JumpData was successfully inserted or updated
 * @property INCORRECT_DATE if the date is incorrect
 * @property INCORRECT_COUNT if the count of jumps is incorrect
 * @property INCORRECT_DATA if the jump data is incorrect
 * @property NOTHING if there is no status
 */
enum class JumpStatus {
    SUCCESS,
    INCORRECT_DATE,
    INCORRECT_COUNT,
    INCORRECT_DATA,
    NOTHING
}