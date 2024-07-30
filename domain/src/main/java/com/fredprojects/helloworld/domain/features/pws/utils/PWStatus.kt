package com.fredprojects.helloworld.domain.features.pws.utils

/**
 * PWStatus represents the status of the PracticalWork
 * @property SUCCESS if the PWUseCase was successfully inserted or updated
 * @property INCORRECT_PW_NAME if the PW name is incorrect
 * @property INCORRECT_STUDENT if the student is incorrect
 * @property INCORRECT_LEVEL if the level is incorrect
 * @property INCORRECT_VARIANT if the variant is incorrect
 * @property INCORRECT_DATE if the date is incorrect
 * @property INCORRECT_MARK if the mark is incorrect
 * @property INCORRECT_IMAGE if the image is incorrect
 * @property NOTHING if the practical work was not verified
 */
enum class PWStatus {
    SUCCESS,
    INCORRECT_PW_NAME,
    INCORRECT_STUDENT,
    INCORRECT_LEVEL,
    INCORRECT_VARIANT,
    INCORRECT_DATE,
    INCORRECT_MARK,
    INCORRECT_IMAGE,
    NOTHING
}