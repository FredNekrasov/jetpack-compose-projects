package com.fredprojects.domain.features.fibonacci.utils

/**
 * FibonacciStatus represents the status of the calculation of the fibonacci sequence for a given number
 * @property SUCCESS if the calculation was successfully completed
 * @property INIT if the calculation is in progress
 * @property ERROR if the calculation failed
 */
enum class FibonacciStatus {
    SUCCESS,
    INIT,
    ERROR
}