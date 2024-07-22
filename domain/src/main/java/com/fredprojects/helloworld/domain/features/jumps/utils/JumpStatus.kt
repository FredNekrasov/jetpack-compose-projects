package com.fredprojects.helloworld.domain.features.jumps.utils

/**
 * JumpStatus represents the status of the JumpData
 * @property SUCCESS if the JumpData was successfully inserted or updated
 * @property INCORRECT_DATE if the date is incorrect
 * @property INCORRECT_COUNT if the count is incorrect
 * @property INCORRECT_DATA if the data is incorrect
 */
enum class JumpStatus {
    SUCCESS,
    INCORRECT_DATE,
    INCORRECT_COUNT,
    INCORRECT_DATA
}