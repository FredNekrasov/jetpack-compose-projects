package com.fredprojects.features.fibonacci.api.utils

/**
 * The CalculationStatus is the status of the Fibonacci sequence calculation
 * @property SUCCESS if the calculation was successfully completed
 * @property INIT if the calculation is in progress
 * @property ERROR if the calculation failed
 */
enum class CalculationStatus {
    SUCCESS,
    INIT,
    ERROR
}